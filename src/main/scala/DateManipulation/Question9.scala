package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, months_between, year}

object Question9 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question9")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df =Seq(("2024-01-01", "2024-04-01"), ("2024-05-01", "2024-08-01")).toDF("start_date", "end_date")


    val res = df.select(months_between(col("start_date"), col("end_date")))
    res.show()











  }

}
