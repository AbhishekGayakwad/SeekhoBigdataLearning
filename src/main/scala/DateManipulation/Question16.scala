package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{add_months, col, month, when}

object Question16 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question13")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-01-01"), ("2024-02-15")).toDF("date")



    val res = df.select(add_months(col("date"),5).alias("Month"))
    res.show()











  }

}
