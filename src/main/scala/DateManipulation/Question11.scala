package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, date_format, dayofweek}

object Question11 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question11")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-09-01"), ("2024-09-10")).toDF("date")


    val res = df.select(date_format(col("date"),"dd-MM-yyyy"))
    res.show()











  }

}
