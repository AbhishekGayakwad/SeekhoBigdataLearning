import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, count, date_format, max, min, sum, to_date, unix_timestamp}

object Groupby9 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby9")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


      val df= List(
        ("2023-04-10","1","100"),
        ("2023-04-11","2","200"),
        ("2023-04-12","3","300"),
        ("2023-04-13","1","400"),
        ("2023-04-14","2","500")
      ).toDF("orderDate","customerId","amount")

    val rs = df.groupBy(col("customerId"),col("city")).agg(count(col("orderId")))

    rs.show()


  }

}