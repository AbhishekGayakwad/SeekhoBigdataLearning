package GroupBy

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col}

object Groupby6 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby6")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


      val df= List(
        (1,"USA","order_1",100),
          (1,"USA","order_2",200),
          (2,"UK","order_3",150),
          (3,"France","order_4",250),
          (3,"France","order_5",300)
      ).toDF("customerId","country","orderId","amount")

    val rs = df.groupBy(col("customerId"),col("country")).agg(avg(col("amount")))

    rs.show()


  }

}