package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, date_add, date_sub}

object Question5 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question5")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df =Seq(("2024-06-10"), ("2024-06-20")).toDF("date")


    val res = df.select(date_sub(col("date"),7))
    res.show()











  }

}
