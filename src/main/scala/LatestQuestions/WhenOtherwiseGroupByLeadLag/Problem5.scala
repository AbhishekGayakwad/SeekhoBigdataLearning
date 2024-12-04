package LatestQuestions.WhenOtherwiseGroupByLeadLag

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object Problem5 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("Example3")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    val schema1 = StructType(Array(
      StructField("session_id", StringType),
      StructField("user_id", StringType),
      StructField("page_views", IntegerType),
      StructField("session_date", DateType),
      StructField("device_type", StringType),
      StructField("traffic_source", StringType)
    ))
    val df = spark.read
      .option("header", "true")
      .schema(schema1)
      .csv("C:/Users/Admin/Desktop/SeekhoBigData/Data/WhenOtherwiseGroupByLeadLag/Question5.csv")

    val window = Window.partitionBy(col("session_id")).orderBy(col("session_date"))
    df.show()
    val newcolumnDf = df.withColumn("session_month",month(col("session_date")))

    newcolumnDf.show()

    val filterDf = newcolumnDf.filter(col("traffic_source")===("Organic") && col("device_type")==="Mobile")
    filterDf.show()

    val groupByDf = newcolumnDf.groupBy(col("device_type"),col("session_month")).agg(sum(col("page_views")).alias("total"),
      max((col("page_views"))),count("session_id"))

    groupByDf.show()


    val leadDf = newcolumnDf.withColumn("leadCol",lead("page_views",1).over(window))
      .withColumn("lagCol",lag("page_views",1).over(window))
    leadDf.show()
    spark.stop()

  }
}
