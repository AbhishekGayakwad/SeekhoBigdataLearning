package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, date_add, datediff, round}

object Question20 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question13")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-01-01", "2024-02-01"), ("2024-03-01", "2024-04-01")).toDF("start_date", "end_date")



    val res = df.select(round(datediff(col("end_date"),col("start_date"))/7))
    res.show()











  }

}
