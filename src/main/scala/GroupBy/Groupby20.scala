package GroupBy

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Groupby20 {
  def main(args: Array[String]): Unit = {
    val logger: Logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("Groupby20")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df= List(
      ("Laptop","Electronics",1000),
      ("Phone","Electronics",500),
      ("T-Shirt","Clothing",20),
      ("Jeans","Clothing",50),
      ("Chair","Furniture",150),
      ("Sofa","Furniture",200)
    ).toDF("product","category","price")


   val res = df.groupBy(col("category")).agg(sum(col("price")).alias("total"),count(col("price")).alias("count"),avg(col("price")).alias("mean"))
   val joinedDf = res.join(df,"category")
   val res_varianc = joinedDf.withColumn("minus",col("price")-col("mean")).withColumn("variance",(col("minus")*col("minus")))
     .withColumn("standard_deviation",sqrt(col("variance")/col("count")-1))

    res_varianc.select(col("category"),col("standard_deviation")).distinct().show()


  }

}