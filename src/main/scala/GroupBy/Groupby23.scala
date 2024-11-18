package GroupBy

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object Groupby23 {
  def main(args: Array[String]): Unit = {
    val logger: Logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("Groupby23")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df= List(
      (1,"2022-01-01",4),
      (1,"2023-02-15",3),
      (2,"2022-01-10",5),
      (2,"2023-02-20",4),
      (3,"2022-01-20",4),
      (3,"2023-02-25",5)
    ).toDF("productId","orderDate","rating")

   val res = df.groupBy(col("productId"),month(col("orderDate")).alias("month")).agg(avg(col("rating")).alias("avg"))
    val win = Window.partitionBy(col("productId")).orderBy(col("productId"),col("month"))
    val rs = res.select(col("productId"),col("month"),col("avg"))
                .withColumn("previous",lag("avg",1).over(win))
                .filter(col("avg")<col("previous"))


    rs.show()


  }

}