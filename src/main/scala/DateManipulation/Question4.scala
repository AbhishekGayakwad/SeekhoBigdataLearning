package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, date_add, datediff}

object Question4 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question4")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-05-01"), ("2024-05-15")).toDF("date")

    val res = df.select(date_add(col("date"),10))
    res.show()











  }

}
