import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, dayofweek, when}

object Groupby10 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby10")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


      val df= List(
        ("T-shirt","Clothing","20"),
        ("Table","Furniture","150"),
        ("Jeans","Clothing","50"),
        ("Chair","Furniture","100")
      ).toDF("product","category","price")

    val rs = df.filter(col("product").startsWith("T")).groupBy(col("category")).agg(avg(col("price")))

    rs.show()


  }

}