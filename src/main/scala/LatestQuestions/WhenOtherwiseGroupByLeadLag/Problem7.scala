package LatestQuestions.WhenOtherwiseGroupByLeadLag

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object Problem7 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("Example3")
    val spark = SparkSession.builder().config(conf).getOrCreate()


    val df = spark.read
      .option("header", "true")
      .option("inferSchema","true")
      .csv("C:/Users/Admin/Desktop/SeekhoBigData/Data/WhenOtherwiseGroupByLeadLag/Question7.csv")

    val window = Window.partitionBy(col("patient_id")).orderBy(col("visit_date"))
    df.show()
    df.printSchema()
    val newcolumnDf = df.withColumn("visit_year",year(col("visit_date")))

    newcolumnDf.show()

    val filterDf = newcolumnDf.filter(col("department").startsWith("Cardio") && col("treatment_cost")>500)
    filterDf.show()

    val groupByDf = newcolumnDf.groupBy(col("doctor_name"),col("visit_year")).agg(sum(col("treatment_cost")).alias("total")
      ,count("Visit_id"),max("treatment_cost"))

    groupByDf.show()


    val leadDf = newcolumnDf.withColumn("leadCol",lead("treatment_cost",1).over(window))
    leadDf.show()
    spark.stop()

  }
}
