package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, last_day, year}

object Question8 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question8")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df =Seq(("2024-01-01"), ("2025-02-01")).toDF("date")


    val res = df.select(year(col("date")))
    res.show()











  }

}
