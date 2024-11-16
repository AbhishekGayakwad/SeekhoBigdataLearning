import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, when}

object Leadexample2 {

  def main(args:Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("example2")
      .getOrCreate()
    import spark.implicits._
    val ls = List(
      (1,"John",1000,"2016-01-01"),
      (1,"John",2000,"2016-01-02"),
      (1,"John",1000,"2016-01-03"),
      (1,"John",2000,"2016-01-04"),
      (1,"John",3000,"2016-01-05"),
      (1,"John",1000,"2016-01-06")
    ).toDF("ID","NAME","SALARY","DATE")

    val window1=Window.orderBy(col("SALARY"))

    val df3= ls.withColumn("lag_column",lag(col("SALARY"),1).over(window1))

    val df4 = df3.select(col("salary"),when(col("lag_column")>col("SALARY"),"Up").otherwise("down"))
    //df4.show()


    ls.createOrReplaceTempView("temp1")

    val dfsql1 = spark.sql(

      """
         select ID,NAME,SALARY,DATE, case when lag_column>SALARY then 'Up' else 'down' end as latest from
         (
         select ID,NAME,SALARY,DATE,lag(SALARY,1) over( order by DATE) as lag_column  from temp1
         )

        """)

    dfsql1.show()


  }


}
