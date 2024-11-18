package GroupBy

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Groupby25 {
  def main(args: Array[String]): Unit = {
    val logger: Logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("Groupby25")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df= List(
      ("USA","order_1",100,"Shipped"),
      ("USA","order_2",40,"Cancelled"),
      ("UK","order_3",150,"Completed"),
      ("France","order_4",250,"Pending"),
      ("France","order_5",30,"Shipped")
    ).toDF("country","orderId","amount","status")

   val res = df.filter(col("status")=!="Cancelled").groupBy(col("country")).agg(avg(col("amount")).alias("avg"))
    res.show()


  }

}