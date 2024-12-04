  package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{abs, col, date_add, date_format, dayofweek, expr, lit, next_day, when}

  object Question23 {
    def main(args:Array[String]): Unit = {
      //val logger: Logger = Logger.getLogger(this.getClass)

      val logger = Logger.getLogger(this.getClass)

      val spark = SparkSession.builder()
        .appName("question13")
        .master("local[*]")
        .getOrCreate()

      import spark.implicits._
      val df = Seq(("2024-10-10"), ("2024-10-15")).toDF("date")


     // val res = df.withColumn("x",date_add(col("date"),dayofweek(col("date")).cast("integer")))
     val res = df.select(col("date"),next_day(col("date"),"Sun"))
      //
      //     when(col("date").notEqual(""),abs(dayofweek(col("date")) - 7)).alias("x"))
//
//     val res1 = res.select(col("date"),col("x"),
//       when(col("x")>0,date_add(col("date"), col("x"))).otherwise(col("date")))
     res.show()

    }

  }
