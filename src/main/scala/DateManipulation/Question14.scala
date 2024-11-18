package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, quarter, when, year}

object Question14 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question14")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-03-15"), ("2024-06-20")).toDF("date")


    val res = df.select(quarter(col("date")))
    res.show()











  }

}
