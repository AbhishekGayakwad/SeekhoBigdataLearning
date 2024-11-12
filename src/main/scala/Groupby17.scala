import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Groupby17 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby17")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
      val df= List(
        (1,"2023-01-01"),
        (1,"2023-02-15"),
        (2,"2023-03-10"),
        (2,"2023-03-20"),
        (3,"2023-04-20"),
        (3,"2023-05-05")
      ).toDF("customerId","orderDate")

    val rs = df.groupBy(col("customerId")).agg(countDistinct(month(col("orderDate"))).alias("distinctMonths")).filter(col("distinctMonths")>=2)

    
    rs.show()
  }

}