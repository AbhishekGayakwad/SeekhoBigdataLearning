package GroupBy

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Groupby16 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby16")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
      val df= List(
        (1,"order_1",4,2),
        (1,"order_2",5,1),
        (2,"order_3",3,4),
        (2,"order_4",4,3),
        (2,"order_5",5,1),
          (3,"order_5",5,1)
      ).toDF("productId","orderId","rating","quantity")

    val rs = df.groupBy(col("productId")).agg(avg(col("rating")),sum(col("quantity")))

    
    rs.show()
  }

}