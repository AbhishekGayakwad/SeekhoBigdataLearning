package Leadlag

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, lead}

object Leadexample9 {
  def main(args:Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Example9")
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

    val newdf = df.withColumn("previoussalary",lag(col("salary"),1).over(window))
  .withColumn("leadsalary",lead(col("salary"),1).over(window))
      .filter(col("salary")-col("previoussalary")>=500)

    newdf.show()

    df.createOrReplaceTempView("temp")

    val sqldf = spark.sql(
      """ select id,name,salary,previoussalary,leadsalary from (
          select id,name,salary,Lag(salary,1) over ( order by id) as previoussalary,
          Lead(salary,1) over (order by id) as leadsalary
         from temp) where (salary -previoussalary) >= 500


         """)

    sqldf.show()


  }

}
