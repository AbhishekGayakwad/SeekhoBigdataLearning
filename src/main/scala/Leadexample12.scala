import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, sum,max}

object Leadexample12 {
  def main(args:Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Example12")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    val df= List(
      (1,"Karthik",1000),
      (2,"Mohan",2000),
      (3,"Vinay",1500),
      (4,"Deva",3000)
    ).toDF("id","name","salary")


    val window = Window.partitionBy(col("name")).orderBy(col("id"))

    val newdf = df.withColumn("MaxSalary",max(col("salary")).over(window))

    newdf.show()

    df.createOrReplaceTempView("temp")

    val sqldf = spark.sql(
      """ select id,name,salary,max(salary) over (partition by name order by id) as MaxSalary from temp
         """)

    sqldf.show()


  }

}
