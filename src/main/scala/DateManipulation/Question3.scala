package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, date_sub, datediff, dayofweek, when}

object Question3 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question3")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-01-01", "2024-01-10"), ("2024-02-01", "2024-02-20")).toDF("start_date", "end_date")

    val res = df.select(datediff(col("start_date"),col("end_date")))
    res.show()











  }

}
