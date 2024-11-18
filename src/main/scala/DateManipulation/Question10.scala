package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, dayofweek, months_between}

object Question10 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question10")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-08-15"), ("2024-08-21")).toDF("date")


    val res = df.select(dayofweek(col("date")))
    res.show()











  }

}
