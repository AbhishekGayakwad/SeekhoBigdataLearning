  package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, current_date, date_format, when, year}

object Question22 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question13")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-09-30"), ("2024-10-01")).toDF("date")



    val res = df.select(date_format(col("date"),"yyyy-MM-dd HH:mm"))
    res.show()











  }

}
