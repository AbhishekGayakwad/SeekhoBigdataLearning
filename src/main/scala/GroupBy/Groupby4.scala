package GroupBy

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, sum}

object Groupby4 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby4")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


    val df= List(
      ("Laptop","order_1",2),
        ("phone","order_2",1),
        ("T-shirt","order_1",3),
        ("Jeans","order_3",4),
        ("Chair","order_2",2)
    ).toDF("product","orderId","quantity")

    val rs = df.groupBy(col("product"),col("orderId")).agg(sum(col("quantity")))

    rs.show()


  }

}