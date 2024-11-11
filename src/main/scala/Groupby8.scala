import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, count, date_format, max, min, sum, to_date, unix_timestamp}

object Groupby8 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby8")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


      val df= List(
        ("1","New York","order_1"),
        ("1","New York","order_1")
      ).toDF("customerId","city","orderId")

    val rs = df.groupBy(col("customerId"),col("city")).agg(count(col("orderId")))

    rs.show()


  }

}