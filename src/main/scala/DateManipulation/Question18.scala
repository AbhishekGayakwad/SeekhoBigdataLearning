package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{ceil, col, date_sub, hour, round}

object Question18 {
  def main(args:Array[String]): Unit = {
    //val logger: Logger = Logger.getLogger(this.getClass)

    val logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("question13")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df = Seq(("2024-03-10 12:25:30"), ("2024-03-10 12:55:45")).toDF("timestamp")



    val res = df.select(round(hour(col("timestamp"))+1).alias("datetime"))
    res.show()











  }

}
