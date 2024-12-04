package LatestQuestions.WhenOtherwiseGroupByLeadLag

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object Problem2 {

  def main(args:Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("problem2")
      .setMaster("local[*]")

    val spark = SparkSession.builder()
      .config(conf)
      .getOrCreate()

    import spark.implicits._
    val df = List(("C001","Premium Gold","Yes","2023-12-01",1200,"USA"),
      ("C002","Basic","No",null,400,"Canada"),
      ("C003","Premium Silver","Yes","2023-11-15",800,"UK"),
      ("C004","Premium Gold","Yes","2024-01-10",1500,"USA"),
      ("C005","Basic","No",null,300,"IT INDIA"))
      .toDF("customer_id","subscription_type","churn_status","churn_date","revenue","country")

    val window = Window.partitionBy(col("country")).orderBy(col("churn_year"))
    val dfNewColumn =  df.withColumn("churn_year",year(col("churn_date")))

    val  filteredDf = dfNewColumn.filter(lower(col("subscription_type")).startsWith("premium") && col("churn_date").isNotNull)
    filteredDf.show()

    val  groupByDf = dfNewColumn.groupBy(col("country"),col("churn_year")).agg(sum(col("revenue")).alias("sum"),count(col("customer_id")))

    groupByDf.show()

    val  groupByDf1 = dfNewColumn.groupBy(col("customer_id")).agg(avg(col("revenue")).alias("avg"))

    val windowDf = dfNewColumn.withColumn("leadColumn",lead(col("revenue"),1).over(window))
    groupByDf1.show()

    windowDf.show()

    spark.stop()
  }

}
