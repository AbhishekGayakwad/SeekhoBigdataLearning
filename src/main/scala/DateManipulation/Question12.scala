package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, date_format, dayofweek, when}

object Question12 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question12")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-10-12"), ("2024-10-13")).toDF("date")


    val res = df.select(when(dayofweek(col("date"))=== "6" || dayofweek(col("date"))=== "7","True").otherwise("False"))
    res.show()











  }

}
