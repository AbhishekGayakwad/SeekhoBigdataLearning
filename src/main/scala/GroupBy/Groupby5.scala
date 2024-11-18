package GroupBy

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col}

object Groupby5 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Groupby5")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._


      val df= List(
        (1,1,4),
          (1,2,5),
          (2,1,3),
          (2,3,4),
          (3,2,5)
      ).toDF("userId","productId","rating")

    val rs = df.groupBy(col("userId")).agg(avg(col("rating")))

    rs.show()


  }

}