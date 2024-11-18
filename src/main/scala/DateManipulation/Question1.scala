package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, dayofmonth, month}

object Question1 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question1")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-01-15"), ("2024-02-20"), ("2024-03-25")).toDF("date")

    val res = df.select(dayofmonth(col("date")).alias("month"))
    res.show()











  }

}
