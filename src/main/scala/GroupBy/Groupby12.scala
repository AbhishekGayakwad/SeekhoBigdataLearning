package GroupBy

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, sum, when}

object Groupby12 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby12")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


      val df= List(
        ("order_1","150"),
        ("order_2","80"),
        ("order_3","220"),
        ("order_4","50")
      ).toDF("orderId","amount")

    val rs = df.groupBy(col("orderId")).agg(sum(col("amount")).alias("total")).withColumn("orderStatus",when(col("total")>100,"High").otherwise("Low"))

    rs.show()


  }

}