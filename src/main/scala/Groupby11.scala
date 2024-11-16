import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, sum}

object Groupby11 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby11")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


      val df= List(
        (1,"order_1","100"),
        (1,"order_2","150"),
        (2,"order_3","250"),
        (3,"order_4","100"),
        (3,"order_5","120")
      ).toDF("customerId","orderId","amount")

    val rs = df.groupBy(col("customerId")).agg(sum(col("amount")).alias("total")).filter(col("total")>200)
    rs.show()


  }

}