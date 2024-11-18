package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, dayofmonth, dayofweek, when}

object Question2 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question2")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df =  Seq(("2024-04-02"), ("2024-04-03"), ("2024-04-04")).toDF("date")

    val res = df.select(when(dayofweek(col("date")).alias("Week Name")===1,"Sunday")
      .when(dayofweek(col("date")).alias("month")===2,"Monday")
    .when(dayofweek(col("date")).alias("month")===3,"Tuesday")
    .when(dayofweek(col("date")).alias("month")===4,"Wednesday")
    .when(dayofweek(col("date")).alias("month")===5,"Thursday")
    .when(dayofweek(col("date")).alias("month")===6,"Friday")
      .when(dayofweek(col("date")).alias("month")===7,"Saturday")
      .otherwise("Wrong number").alias("Week Name")
    )
    res.show()











  }

}
