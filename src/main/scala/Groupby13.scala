import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, sum, when}

object Groupby13 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby13")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
      val df= List(
        ("Laptop","Electronics",1000,2),
        ("phone","Electronics",500,1),
        ("T-shirt","clothing",20,3),
        ("Jeans","clothing",50,4)
      ).toDF("product","category","price","quantity")

    val rs = df.select(col("product"),col("price")).groupBy(col("product")).agg(avg(col("price")).alias("total"))
    
    rs.show()
  }

}