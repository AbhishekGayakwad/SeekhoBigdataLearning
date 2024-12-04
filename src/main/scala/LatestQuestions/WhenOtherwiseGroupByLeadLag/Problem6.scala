package LatestQuestions.WhenOtherwiseGroupByLeadLag

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object Problem6 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("Example3")
    val spark = SparkSession.builder().config(conf).getOrCreate()


    val df = spark.read
      .option("header", "true")
      .option("inferSchema","true")
      .csv("C:/Users/Admin/Desktop/SeekhoBigData/Data/WhenOtherwiseGroupByLeadLag/Question6.csv")

    val window = Window.partitionBy(col("product_name")).orderBy(col("return_date"))
    df.show()
    df.printSchema()
    val newcolumnDf = df.withColumn("return_year",year(col("return_date")))

    newcolumnDf.show()

    val filterDf = newcolumnDf.filter(col("product_name").startsWith("Electro") && col("refund_amount").isNotNull)
    filterDf.show()

    val groupByDf = newcolumnDf.groupBy(col("return_reason"),col("return_year")).agg(sum(col("refund_amount")).alias("total")
      ,count("return_reason"),avg(col("refund_amount")))

    groupByDf.show()


    val lagDf = newcolumnDf.withColumn("lagCol",lag("refund_amount",1).over(window))
    lagDf.show()
    spark.stop()

  }
}
