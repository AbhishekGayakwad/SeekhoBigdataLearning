import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, lead}

object Leadexample3
{
  def main(args:Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Example3")
      .master("local[*]")
      .getOrCreate()

    import  spark.implicits._

    val df= List(
      (1,"Karthik",1000),
      (2,"Mohan",2000),
      (3,"Vinay",1500),
      (4,"Deva",3000)
    ).toDF("id","name","salary")


    val window = Window.orderBy(col("id"))

    val newdf = df.withColumn("leadColumn",lead(col("salary"),1).over(window))
      .withColumn("lagColumn",lag(col("salary"),1).over(window))

    newdf.show()


    df.createOrReplaceTempView("temp")

    val sqldf = spark.sql(
      """ select id,name,salary, lead(salary,1) over (order by id) as leadColumn,
        Lag(salary,1) over (order by id) as lagColumn
         from temp
         """)

    sqldf.show()


  }

}
