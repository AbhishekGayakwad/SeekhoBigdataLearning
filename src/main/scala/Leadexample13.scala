import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{avg, col, max}

object Leadexample13 {
  def main(args:Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Example13")
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

    val newdf = df.withColumn("avgSalary",avg(col("salary")).over(window))

    val newdf2 = newdf.withColumn("diffsalaryavg",col("salary")-col("avgSalary"))

    newdf2.show()

    df.createOrReplaceTempView("temp")

    val sqldf = spark.sql(
       """
           select id,name,salary, salary-avgSalary from (
           select id,name,salary,avg(salary) over (partition by name order by id) as avgSalary from temp)
         """)

    sqldf.show()


  }

}
