package GroupBy

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Groupby24 {
  def main(args: Array[String]): Unit = {
    val logger: Logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("Groupby24")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df= List(
      ("order_1","2023-04-10",10),
      ("order_2","2023-04-11",15),
      ("order_3","2023-04-12",12),
      ("order_4","2023-04-13",11),
      ("order_1","2023-04-14",18),
      ("order_2","2023-04-14",18)
    ).toDF("orderid","orderDate","hour")

   val res = df.groupBy(dayofweek(col("orderDate")),col("hour")).agg(count(col("orderid")).alias("total")).orderBy(col("total").desc).limit(1)

    res.show()


  }

}