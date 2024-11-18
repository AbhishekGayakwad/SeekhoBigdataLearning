//import org.apache.spark.sql.SparkSession
//
//object third {
//  def main(args: Array[String]): Unit = {
//    // Initialize Spark session
//    val spark = SparkSession.builder.master("local[*]").appName("ReadArgsExample").getOrCreate()
//
//    // Read arguments from the command line
//    val inputPath = args(0)  // First argument
//    val outputPath = args(1)  // Second argument
//
//    // Read data from the input path
//    val df = spark.read.option("header", "true").csv(inputPath)
//
//    // Show the DataFrame
//    df.show()
//
//    // Write the DataFrame to the output path
//    df.write.mode("overwrite").option("header", "true").csv(outputPath)
//
//    spark.stop()
//  }
//}