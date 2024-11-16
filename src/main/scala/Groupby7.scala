import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, count, date_format, max, min, sum, to_date, unix_timestamp}

object Groupby7 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby7")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


      val df= List(
        ("Laptop","2023-01-01"),
        ("Phone","2023-02-15"),
        ("T-Shirt","2023-03-10"),
        ("Jeans","2023-04-20")
      ).toDF("product","orderDate")

    val rs = df.filter(to_date(col("orderDate"))<"2023-02-01" || to_date(col("orderDate"))>"2023-03-31")

    rs.show()


  }

}