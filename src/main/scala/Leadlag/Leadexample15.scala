package Leadlag

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, lead}

object Leadexample15 {
  def main(args:Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Example15")
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

    val newdf = df.withColumn("leadSalary",lead(col("salary"),1).over(window))
      .withColumn("lagsalary",lag(col("salary"),1).over(window))
      .filter(col("salary")-col("lagsalary")>0)



    newdf.show()

    df.createOrReplaceTempView("temp")

//    val sqldf = spark.sql(
//       """
//
//           select id,name,salary,rank() over ( order by salary desc)  as rank from temp
//         """)
//
//    sqldf.show()


  }

}
