package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, current_date, datediff, round, when, year}

object Question21 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question13")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-01-15"), ("2023-12-25")).toDF("date")



    val res = df.select(when(year(col("date"))===year(current_date()),"yes").otherwise("no"))
    res.show()











  }

}
