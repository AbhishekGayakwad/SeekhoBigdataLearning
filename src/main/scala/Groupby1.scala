import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, count}

object Groupby1 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby1")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


    val df= List(
      ("Electorincs","Laptop"),
      ("Electorincs","Phone"),
      ("Clothing","T-Shirt"),
      ("Clothing","Jeans"),
      ("Furniture","Chair")
    ).toDF("category","product")


    val df1 = df.groupBy("category").agg(count(col("product")).alias("count"))

  df1.show()


  }

}