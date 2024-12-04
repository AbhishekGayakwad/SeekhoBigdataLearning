package LatestQuestions.WhenOtherwiseGroupByLeadLag

import org.apache.spark.SparkConf
import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{avg, col, datediff, lag, lead, max, min, sum}
import org.apache.spark.sql.types.{DateType, FloatType, IntegerType, StringType, StructField, StructType}

object Problem4 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("Example3")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    val schema1 = StructType(Array(
      StructField("loadId", StringType),
      StructField("customerId", StringType),
      StructField("repayment_amount", IntegerType),
      StructField("due_date", DateType),
      StructField("payment_date", DateType),
      StructField("loan_type", StringType),
      StructField("interest_rate", FloatType)
    ))
    val df = spark.read
      .option("header", "true")
      .schema(schema1)
      .csv("C:/Users/Admin/Desktop/SeekhoBigData/Data/WhenOtherwiseGroupByLeadLag/Question4.csv")

    val window = Window.partitionBy(col("customerId")).orderBy(col("customerId"),col("payment_date"))
    df.show()
    val newcolumnDf = df.withColumn("repaymentDelay",datediff(col("payment_date"),col("due_date")))

    newcolumnDf.show()

    val filterDf = newcolumnDf.filter(col("loan_type").startsWith("Personal") && col("repaymentDelay")>30)
    filterDf.show()

    val groupByDf = newcolumnDf.groupBy(col("loan_type"),col("interest_rate")).agg(sum(col("repayment_amount")).alias("total"),
      max((col("repayment_amount"))),avg(col("interest_rate")))

    groupByDf.show()


    val leadDf = newcolumnDf.withColumn("leadCol",lead("repayment_amount",1).over(window))
    leadDf.show()
    spark.stop()

  }
}
