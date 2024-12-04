package LatestQuestions.WhenOtherwiseGroupByLeadLag

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.functions.{avg, col, count, lag, lead, lower, month}

object Problem1 {

  def main(args:Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("problem1")
      .setMaster("local[*]")

    val spark = SparkSession.builder()
      .config(conf)
      .getOrCreate()

    import spark.implicits._
    val df = List(("E001","Sales","85","2024-02-10","Sales Manager"),
      ("E002","HR","78","2024-03-15","HR yAssistant"),
      ("E003","IT","92","2024-01-22","IT Manager"),
      ("E004","Sales","88","2024-02-18","Sales Rep"),
      ("E005","HR","95","2024-03-20","HR Manager"),
      ("E003","IT","87","2024-12-22","IT Manager"))
      .toDF("empId","department","performanceScore","reviewDate","position")

    val window = Window.partitionBy(col("empId")).orderBy(col("reviewDate"))
    val dfNewColumn =  df.withColumn("reviewMonth",month(col("reviewDate")))

    val  filteredDf = dfNewColumn.filter(lower(col("position")).endsWith("manager") && col("performanceScore")>80)
    val  groupbyDf = filteredDf.groupBy(col("department"),col("reviewMonth")).agg(count(col("empId")).alias("count"),avg(col("performanceScore")).alias("average"))

    groupbyDf.show()

    val windowDf = df.withColumn("lagColumn",lag(col("performanceScore"),1).over(window))
    windowDf.show()

    spark.stop()
  }

}
