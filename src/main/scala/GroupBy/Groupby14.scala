package GroupBy

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Groupby14 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby14")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
      val df= List(
        ("2023-01-01",1,100),
        ("2023-02-15",2,200),
        ("2023-03-10",3,300),
        ("2023-04-20",1,400),
        ("2023-05-05",2,500)
      ).toDF("orderDate","customerId","amount")

    val rs = df.select(col("orderDate"),col("amount"),year(col("orderDate")).alias("year"),month(col("orderDate")).alias("month")).withColumn("year-month",concat(col("year"),lit("-"), col("month")))
      .groupBy(col("year-month")).agg(count(col("orderDate")),sum(col("amount")))

    
    rs.show()
  }

}