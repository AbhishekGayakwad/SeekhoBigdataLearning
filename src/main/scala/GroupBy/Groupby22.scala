package GroupBy

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Groupby22 {
  def main(args: Array[String]): Unit = {
    val logger: Logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("Groupby22")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df= List(
      (1,"2022-01-01",100),
      (1,"2023-02-15",200),
      (2,"2022-03-10",300),
      (2,"2023-04-20",400),
      (3,"2022-05-05",500),
      (3,"2023-06-06",600)
    ).toDF("customerID","orderDate","amount")

   val res = df.groupBy(col("customerID"),year(col("orderDate"))).agg(sum(col("amount")).alias("total"))



    res.show()


  }

}