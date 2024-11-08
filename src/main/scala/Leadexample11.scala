import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, lead, row_number, sum}

object Leadexample11 {
  def main(args:Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Example11")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    import spark.implicits._

    val df= List(
      (1,"Karthik",1000),
      (2,"Mohan",2000),
      (3,"Vinay",1500),
      (4,"Deva",3000)
    ).toDF("id","name","salary")


    val window = Window.orderBy(col("id"))

    val newdf = df.withColumn("Runningtotal",sum(col("salary")).over(window))



    newdf.show()

    df.createOrReplaceTempView("temp")

    val sqldf = spark.sql(
      """ select id,name,salary,sum(salary) over (order by id) as RollingSum from temp
         """)

    sqldf.show()


  }

}
