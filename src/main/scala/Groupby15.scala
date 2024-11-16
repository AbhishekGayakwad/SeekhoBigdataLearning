import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Groupby15 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby15")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
      val df= List(
        ("Laptop","Electronics",2),
        ("Phone","Electronics",1),
        ("T-Shirt","Clothing",3),
        ("Jeans","Clothing",4),
        ("Chair","Furniture",2),
        ("Sofa","Furniture",1)
      ).toDF("product","category","quantity")

    val rs = df.groupBy(col("category")).agg(sum(col("quantity")).alias("totalQuantity")).orderBy(desc("totalQuantity")).limit(2)

    
    rs.show()
  }

}