package LatestQuestions.WhenOtherwiseGroupByLeadLag

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DateType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, count, lag, min, sum}

object Problem3 {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("Example3")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    val schema1 = StructType(Array(
      StructField("salesperson_id", StringType),
      StructField("sales_amount", IntegerType),
      StructField("target_amount", IntegerType),
      StructField("sale_date", DateType),
      StructField("region", StringType),
      StructField("product_category", StringType)
    ))
    val df = spark.read
      .option("header", "true")
      .schema(schema1)
      .csv("C:/Users/Admin/Desktop/SeekhoBigData/Data/WhenOtherwiseGroupByLeadLag/Question3.csv")

    val newColDf = df.withColumn("targetAchieved",col("sales_amount") >= col("target_amount"))
    val window = Window.partitionBy(col("salesperson_id")).orderBy("sale_date")

    val filteredDf = newColDf.filter(col("product_category").startsWith("Electronics")  && col("product_category").endsWith("Accessories") )
    filteredDf.show()
    val groupByDf = filteredDf.groupBy(col("region"),col("product_category")).agg(sum(col("sales_amount")))
    val groupByDf1 = filteredDf.groupBy(col("region"),col("product_category")).agg(min(col("sales_amount")))
    val groupByDf2 = filteredDf.filter(col("targetAchieved") === true).count()
    groupByDf.show()
    groupByDf1.show()
    println(groupByDf2)

    val lagDf = df.withColumn("lagColumn",lag(col("sales_amount"),1).over(window))

    lagDf.show()
    spark.stop()

  }
}
