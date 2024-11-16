import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, count, max, min}

object Groupby3 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby3")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


    val df= List(
      ("2023-01-01","New York",100),
        ("2023-02-15","London",200),
        ("2023-03-10","Paris",300),
        ("2023-04-20","Berlin",400),
        ("2023-05-05","Tokyo",500)
    ).toDF("orderDate","city","amount")



    val df1 = df.withColumn("year",col("orderDate").substr(0,4))

      .withColumn("month",col("orderDate").substr(6,2))
      .groupBy(col("year"),col("month")).agg(count(col("amount")))
    df1.show()

  }

}