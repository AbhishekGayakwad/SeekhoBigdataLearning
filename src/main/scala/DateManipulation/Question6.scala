package DateManipulation


import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, date_sub, year}

object Question6 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question6")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2023-08-12"), ("2024-08-15")).toDF("date")


    val res = df.filter(year(col("date"))==="2023")
    res.show()











  }

}
