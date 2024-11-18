package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, last, last_day, year}

object Question7 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question7")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-07-10"), ("2024-07-25")).toDF("date")


    val res = df.select(last_day(col("date")))
    res.show()











  }

}
