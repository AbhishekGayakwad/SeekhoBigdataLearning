import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, lead}

object Leadexample4 {
  def main(args:Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Example4")
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

    val newdf = df.withColumn("previousSalary",lag(col("salary"),1).over(window))

    val newdf2 = newdf.withColumn("percentageChange",
      ((col("salary")-col("previousSalary"))/col("previousSalary"))*100)


    newdf2.show()

    df.createOrReplaceTempView("temp")

    val sqldf = spark.sql(
      """ select id,name,salary,previousSalary,((salary-previousSalary)/previousSalary)*100 as precentageChange from (
          select id,name,salary,Lag(salary,1) over (order by id) as previousSalary
         from temp)


         """)

    sqldf.show()


  }

}
