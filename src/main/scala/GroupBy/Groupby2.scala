package GroupBy

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, max, min}

object Groupby2 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby2")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


    val df= List(
      ("Laptop",1000),
      ("Phone",500),
      ("T-Shirt",20),
      ("Jeans",50),
      ("Chair",150)
    ).toDF("product","price")


    val df1 = df.groupBy("product")
      .agg(min(col("price")).alias("min"))


    val df2 = df.groupBy("product")
      .agg(max(col("price")).alias("max"))

    val df3 = df.groupBy("product")
      .agg(avg(col("price")).alias("avg"))




  df1.show()
        df2.show()
        df3.show()



  }

}