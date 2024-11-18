package GroupBy

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object Groupby21 {
  def main(args: Array[String]): Unit = {
    val logger: Logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("Groupby21")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df= List(
      (1,"New York"),
      (1,"New York"),
      (2,"London"),
      (2,"Paris"),
      (3,"Paris"),
      (3,"Paris")
    ).toDF("customerID","city")

//
//
//    val df1 = df.withColumn("rank",rank().over(window))


   val res = df.groupBy(col("customerID"),col("city")).agg(count(col("city")).alias("count"))

    val window = Window.partitionBy(col("customerId")).orderBy(col("city"))

    val res2 = res.withColumn("rank",rank().over(window)).filter(col("rank")===1)

    res2.show()


  }

}