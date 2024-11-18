package GroupBy

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Groupby18 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby18")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df= List(
      ("USA","order_1",100),
      ("USA","order_2",40),
      ("UK","order_3",150),
      ("France","order_4",250),
      ("France","order_5",30)
    ).toDF("country","orderId","amount")

    val rs = df.filter(col("amount")>=50).groupBy(col("country")).agg(sum(col("amount")).alias("total"))

    
    rs.show()
  }

}