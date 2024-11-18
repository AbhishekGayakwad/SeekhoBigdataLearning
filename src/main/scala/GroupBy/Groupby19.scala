package GroupBy

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Groupby19 {
  def main(args: Array[String]): Unit = {
    val logger: Logger = Logger.getLogger(this.getClass)

    val spark = SparkSession.builder()
      .appName("Groupby19")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val df= List(
      ("order_1",1,2),
      ("order_2",1,3),
      ("order_3",2,4),
      ("order_4",3,1),
      ("order_5",1,4)
    ).toDF("orderId","product_id1","product_id2")

    val pairsDF = df
      .select(
        least(col("product_id1"), col("product_id2")).alias("product_min"),
        greatest(col("product_id1"), col("product_id2")).alias("product_max")
      )
      .distinct()  // To remove duplicates (e.g., (1,2) and (2,1))

    //pairsDF.show()
    val products = df.select(col("product_id1")).union(df.select(col("product_id2"))).distinct()

    val allPairsDF = products.as("p1")
      .crossJoin(products.as("p2"))
      .filter(col("p1.product_id1") < col("p2.product_id1"))  // Ensure no duplicate pairs (e.g., (1,2) and (2,1))
    allPairsDF.show()

    val allPairs = allPairsDF
      .select(
        col("p1.product_id1").alias("product_min"),
        col("p2.product_id1").alias("product_max")
      )

    //allPairs.show()

    val missingPairsDF = allPairs.except(pairsDF)

   // missingPairsDF.show()
    //(1,4),(2,3),(3,4)

  }

}