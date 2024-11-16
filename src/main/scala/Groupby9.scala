import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, count, date_format, dayofweek, max, min, sum, to_date, unix_timestamp, when}

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

    val rs = df.withColumn("weekdayOrWeekend",dayofweek(col("orderDate")))
      .select(col("amount"),when(col("weekdayOrWeekend")>=6 && col("weekdayOrWeekend")<=7,"Weekend")
        .otherwise("Weekday").alias("newcol")).groupBy(col("newcol")).agg(avg(col("amount")))

    rs.show()


  }

}