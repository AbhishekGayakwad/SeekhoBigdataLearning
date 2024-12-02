  package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, date_format, next_day}

  object Question24 {
    def main(args:Array[String]): Unit = {
      //val logger: Logger = Logger.getLogger(this.getClass)

      val logger = Logger.getLogger(this.getClass)

      val spark = SparkSession.builder()
        .appName("question13")
        .master("local[*]")
        .getOrCreate()

      import spark.implicits._
      val df = Seq(("2024-11-15"), ("2024-12-20")).toDF("date")



     val res = df.select(date_format(col("date"), "MMM dd, yyyy"))

     res.show()

    }

  }