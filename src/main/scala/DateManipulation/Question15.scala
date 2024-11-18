package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, month, quarter, when}

object Question15 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question13")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-03-15"), ("2024-06-20")).toDF("date")


    val res = df.select(
      when(month(col("date"))===1,"Jan")
      .when(month(col("date"))===2,"Feb")
      .when(month(col("date"))===3,"Mar")
      .when(month(col("date"))===4,"Apr")
      .when(month(col("date"))===5,"May")
      .when(month(col("date"))===6,"Jun")
      .when(month(col("date"))===7,"Jul")
      .when(month(col("date"))===8,"Aug")
      .when(month(col("date"))===9,"Sep")
      .when(month(col("date"))===10,"Oct")
      .when(month(col("date"))===11,"Nov")
      .when(month(col("date"))===12,"Dec").otherwise("wrong month").alias("Month"))
    res.show()











  }

}
