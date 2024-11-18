package Leadlag

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag}

object Leadexample5 {
  def main(args:Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Example5")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    val df= List(
      (1,"Karthik",1000),
      (2,"Mohan",2000),
      (3,"Vinay",1500),
      (4,"Deva",3000)
    ).toDF("id","name","salary")


    val window = Window.orderBy(col("id"))

    val newdf = df.withColumn("previous2salary",lag(col("salary"),2).over(window))

    val newdf2 = newdf.withColumn("RollingSum ",
      col("salary")+col("previous2salary"))


    newdf2.show()

    df.createOrReplaceTempView("temp")

    val sqldf = spark.sql(
      """ select id,name,salary,previous2salary,(salary+previous2salary) as RollingSum from (
          select id,name,salary,Lag(salary,2) over (order by id) as previous2salary
         from temp)


         """)

    sqldf.show()


  }

}
