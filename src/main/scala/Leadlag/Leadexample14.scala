package Leadlag

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, rank}

object Leadexample14 {
  def main(args:Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Example14")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    val df= List(
      (1,"Karthik",1000),
      (2,"Mohan",2000),
      (3,"Vinay",1500),
      (4,"Deva",3000)
    ).toDF("id","name","salary")


    val window = Window.orderBy(col("salary").desc)

    val newdf = df.withColumn("rank",rank().over(window))



    newdf.show()

    df.createOrReplaceTempView("temp")

    val sqldf = spark.sql(
       """

           select id,name,salary,rank() over ( order by salary desc)  as rank from temp
         """)

    sqldf.show()


  }

}
