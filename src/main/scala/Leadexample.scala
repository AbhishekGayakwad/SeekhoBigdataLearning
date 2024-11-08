import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, when}

object Leadexample {
  def main (args:Array[String]): Unit = {
     val spark = SparkSession.builder()
       .appName("lead")
       .master("local[*]")
       .getOrCreate()

    import spark.implicits._

    val l = List(
      (1, "KitKat",1000.0,"2021-01-01"),
      (1, "KitKat",2000.0,"2021-01-02"),
      (1, "KitKat",1000.0,"2021-01-03"),
      (1, "KitKat",2000.0,"2021-01-04"),
      (1, "KitKat",3000.0,"2021-01-05"),
      (1, "KitKat",1000.0,"2021-01-06")
    ).toDF("IT_ID","IT_NAME","PRICE","PRICEDATE")

    val window=Window.orderBy(col("PRICEDATE"))

    val df2= l.withColumn("lag_column",lag(col("PRICE"),1).over(window))

    //df2.show()


    l.createOrReplaceTempView("temp")

    val dfsql = spark.sql(
      """
         select IT_ID,IT_NAME,PRICE,lag(price,1) over(order by PRICEDATE)  as lag_column from temp
        """)


    dfsql.show()






  }



}
