import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, count, when}

object Question14 {

  def main (args:Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Question14")
      .master("local[*]")
      .getOrCreate()

    import  spark.implicits._

    val loanApplicants = List(
      ("karthik", 60000, 120000, 590),
      ("neha", 90000, 180000, 610),
      ("priya", 50000, 75000, 680),
      ("mohan", 120000, 240000, 560),
      ("ajay", 45000, 60000, 620),
      ("vijay", 100000, 100000, 700),
      ("veer", 30000, 90000, 580),
      ("aatish", 85000, 85000, 710),
      ("animesh", 50000, 100000, 650),
      ("nishad", 75000, 200000, 540)
    ).toDF("name", "income", "loan_amount", "credit_score")

    val df = loanApplicants.select(col("name"),col("income"),col("loan_amount"),col("credit_score"),
      when(col("loan_amount")>= col("income")*2 && col("credit_score") < 600,"High Risk")
        .when((col("loan_amount")>= col("income") && col("loan_amount")<=col("income") *2)
          && (col("credit_score")>=600 && col("credit_score")<=700)," Moderate Risk")
        .otherwise("Low Risk").alias("Risk Classification")
    )

    val df2 = df.groupBy(col("Risk Classification")).agg(count(col("name")).alias("count"))
    //df2.show()

    val df3 = df.filter(col("Risk Classification").equalTo("High Risk"))
      .select(col("name"),col("income"),col("loan_amount"),col("credit_score"),
        when(col("income")<50000,"<50K")
    .when(col("income")>=50000 && col("income")<=100000,"50-100k")
          .otherwise(">100k").alias("incomerange")).groupBy(col("incomerange")).agg(avg(col("loan_amount")))

    //df3.show()

    val df4 = df.select(col("name"),col("income"),col("loan_amount"),col("credit_score"),
      when(col("income")<50000,"<50K")
      .when(col("income")>=50000 && col("income")<=100000,"50-100k")
        .otherwise(">100k").alias("incomerange")).groupBy(col("incomerange")).agg(avg(col("credit_score")))

    //df4.show()

    loanApplicants.createOrReplaceTempView("temp")



    val sdf = spark.sql(
      """
          select name, income, loan_amount, credit_score,
          case when loan_amount >= income*2 and credit_score < 600 then "High Risk"
          when (loan_amount >= income and loan_amount >= income *2) and (credit_score >= 600
           and credit_score <= 700 )then "Moderate Risk"
           else "Low Risk" end as RiskClassification
           from temp
        """)
    //sdf.show()

    sdf.createOrReplaceTempView("temp1")
    //val df2 = df.groupBy(col("Risk Classification")).agg(count(col("name")).alias("count"))

    val sdf2 = spark.sql(
      """select RiskClassification, count(name) as count from temp1
        group by RiskClassification
        """)
    //sdf2.show()





//    val df3 = df.filter(col("Risk Classification").equalTo("High Risk"))
//      .select(col("name"),col("income"),col("loan_amount"),col("credit_score"),
//        when(col("income")<50000,"<50K")
//          .when(col("income")>=50000 && col("income")<=100000,"50-100k")
//          .otherwise(">100k").alias("incomerange")).groupBy(col("incomerange")).agg(avg(col("loan_amount")))

    val sdf3 = spark.sql(
      """select incomerange, avg(loan_amount) from (select loan_amount,case when income < 50000 then '<50K'
         when income >= 50000 and income <=100000 then '50-100k'
          else ">100k" end as incomerange from temp1 where RiskClassification='High Risk'
          )  group by incomerange
        """)
0
   // sdf3.show()

//
//    val df4 = df.select(col("name"),col("income"),col("loan_amount"),col("credit_score"),
//      when(col("income")<50000,"<50K")
//        .when(col("income")>=50000 && col("income")<=100000,"50-100k")
//        .otherwise(">100k").alias("incomerange")).groupBy(col("incomerange")).agg(avg(col("credit_score")))



    val sdf4 = spark.sql(
      """select incomerange, avg(credit_score) from (select credit_score,loan_amount,case when income < 50000 then '<50K'
         when income >= 50000 and income <=100000 then '50-100k'
          else ">100k" end as incomerange from temp1 where RiskClassification='High Risk'
          )  group by incomerange
        """)

    sdf4.show()
//    1. Classify loan applicants as "High Risk" if the loan amount exceeds twice their income and
//      credit score is below 600, "Moderate Risk" if the loan amount is between 1-2 times their
//    income and credit score between 600-700, and "Low Risk" otherwise. Find the total count of
//    each risk level.
//    2. For applicants classified as "High Risk," calculate the average loan amount by income range
//    (e.g., < 50k, 50-100k, >100k).
//    3. Group by income brackets (<50k, 50-100k, >100k) and calculate the average credit score for
//      each risk level. Filter for groups where average credit score is below 65


  }

}
