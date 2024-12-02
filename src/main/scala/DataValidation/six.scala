package DataValidation

import org.apache.spark.SparkContext.getOrCreate
import org.apache.spark.sql.{DataFrame, SparkSession}

object six {

  def main(args: Array[String]): Unit = {

    // Initialize Spark session
    val spark = SparkSession.builder
      .appName("MultiFormatFileValidation")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    // Function to load files dynamically based on file extension
    def loadFileByExtension(filePath: String): DataFrame = {

      val fileExtension = filePath.split("\\.").last.toLowerCase
      fileExtension match {
        case "csv" =>
          spark.read.option("header", "false").csv(filePath)
        case "parquet" =>
          spark.read.parquet(filePath)
        case "avro" =>
          spark.read.format("avro").load(filePath) // Requires spark-avro package
        case _ =>
          throw new IllegalArgumentException(s"Unsupported file format: .$fileExtension")

      }
    }

    loadFileByExtension("C:\\Users\\Admin\\Desktop\\education.csv m").show()

  }
}