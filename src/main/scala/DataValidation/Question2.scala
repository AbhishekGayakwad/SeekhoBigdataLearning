package DataValidation

import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.sql.functions.{avg, col, count}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object Question2 {
  val spark: SparkSession = SparkSession.builder
    .appName("MultiFormatFileValidation")
    .master("local[*]") // Change this according to your cluster settings
    .getOrCreate()

  import spark.implicits._

  // Function to load files dynamically based on file extension
  def loadFileByExtension(filePath: String): DataFrame = {
    val fileExtension = filePath.split("\\.").last.toLowerCase
    val dfDataSchema = StructType(List(
      StructField("Name", StringType, nullable = false),
      StructField("Department", StringType, nullable = false),
      StructField("Gender", StringType, nullable = false)
    ))

    fileExtension match {
      case "txt" =>
        spark.read.option("header", "false").option("delimiter", "|").schema(dfDataSchema).csv(filePath)
      case "parquet" =>
        spark.read.parquet(filePath)
      case "avro" =>
        spark.read.format("avro").load(filePath) // Requires spark-avro package
      case _ =>
        throw new IllegalArgumentException(s"Unsupported file format: .$fileExtension")
    }
  }

  // Function to clean strings and remove any unwanted spaces or invisible characters
  def cleanString(value: String): String = {
    if (value != null) {
      value.trim.replaceAll("[^\\x20-\\x7E]", "") // Removes non-printable characters
    } else {
      "" // Replace null with empty string
    }
  }

  // Function to validate header and trailer based on loaded DataFrame
  def validateHeaderTrailer(df: DataFrame, fileType: String, metadataDF: DataFrame): (Boolean, Dataset[Row]) = {
    val (expectedVersion, expectedRecordCount) = getExpectedMetadata(fileType, metadataDF)

    // Get header and trailer rows
    val header = df.first() // Assuming the header is the first row
    val trailer = df.collect().last // Assuming the trailer is the last row
    val actualRecordCount = df.count() - 2 // Exclude header and trailer rows from the count
    df.show()
    val Df1 = df.filter(col("Name") =!= "EMPLOYEE" && col("Name") =!= "TRAILER")
    Df1.show()

    // Debugging: Print raw header and trailer to inspect values
    println(s"Raw Header: ${header.toSeq}")
    println(s"Raw Trailer: ${trailer.toSeq}")

    // Clean each field of the header and trailer rows
    val cleanedHeader = header.toSeq.take(3).map { // Only take first 3 elements, discard nulls
      case value: String => cleanString(value)
      case other => other.toString // If it's not a String, convert to string before cleaning
    }

    val cleanedTrailer = trailer.toSeq.take(2).map { // Only take first 3 elements, discard nulls
      case value: String => cleanString(value)
      case other => other.toString // If it's not a String, convert to string before cleaning
    }

    // Debugging: Print cleaned header and trailer values to check
    println(s"Cleaned Header: $cleanedHeader")
    println(s"Cleaned Trailer: $cleanedTrailer")
    println(s"Actual Record Count (excluding header and trailer): $actualRecordCount")

    // Compare expected version and record count with cleaned data
    val isHeaderValid = cleanedHeader(0) == fileType &&
      cleanedHeader(1) == expectedVersion &&
      cleanedHeader(2).toInt == expectedRecordCount // Ensure comparison with Int

    // Validate trailer and record count
    val isTrailerValid = cleanedTrailer(0) == "TRAILER" &&
      cleanedTrailer(1).toInt == expectedRecordCount // Ensure trailer record count comparison as Int

    println(s"Header Validation: $isHeaderValid")
    println(s"Trailer Validation: $isTrailerValid")

    (isHeaderValid && isTrailerValid && actualRecordCount == expectedRecordCount, Df1)
  }

  // Metadata function to fetch expected values from metadata DataFrame
  def getExpectedMetadata(fileType: String, metadataDF: DataFrame): (String, Int) = {
    val metadataRow = metadataDF.filter(col("file_type") === fileType).collect().headOption
    metadataRow match {
      case Some(row) =>
        val expectedVersion = row.getString(1)
        val expectedRecordCount = row.getInt(2)
        (expectedVersion, expectedRecordCount)
      case None =>
        throw new Exception(s"No metadata found for file type $fileType")
    }
  }

  // Main function to handle multiple file types and validate header/trailer
  def main(args: Array[String]): Unit = {
    val filePath = "C:\\Users\\Admin\\Desktop\\Seekho BigData\\Data\\Question2.txt" // Path to your file (adjust path)
    val fileType = "EMPLOYEE" // Specify file type for metadata lookup

    try {
      // Load file dynamically based on extension
      val df = loadFileByExtension(filePath)


      // Define sample Metadata Table with expected values for each file type
      val metadataSchema = StructType(List(
        StructField("file_type", StringType, nullable = false),
        StructField("expected_version", StringType, nullable = false),
        StructField("expected_record_count", IntegerType, nullable = false)
      ))

      val metadataData = Seq(
        Row("EMPLOYEE", "v1", 7) // Updated expected record count to 22
      )

      val metadataDF = spark.createDataFrame(
        spark.sparkContext.parallelize(metadataData),
        metadataSchema
      )

      // Validate header and trailer with dynamically loaded data
      val isValid = validateHeaderTrailer(df, fileType, metadataDF)
      println(s"Header and Trailer Validation Result for file type $fileType: ${isValid._1}")

      if (isValid._1) {
        val result = isValid._2.groupBy(col("department"),col("gender")).agg(count(col("name")))
        result.show()
      }
      else{
        println("Validation Failed!! Please check the data file once...")
      }
    } catch {
      case e: Exception => println(s"Validation failed: ${e.getMessage}")
    } finally {
      spark.stop()
    }


  }
}
