//import org.apache.spark.sql.SparkSession
//
//object Question15 {
//
//  def main(args:Array[String]): Unit = {
//    val spark = SparkSession.builder()
//      .appName("Question15")
//      .master("local[*]")
//      .getOrCreate()
//
//    import spark
//    val customerPurchases = List(
//      ("karthik", "Premium", 50, 5000),
//      ("neha", "Standard", 10, 2000),
//      ("priya", "Premium", 65, 8000),
//      ("mohan", "Basic", 90, 1200),
//      ("ajay", "Standard", 25, 3500),
//      ("vijay", "Premium", 15, 7000),
//      ("veer", "Basic", 75, 1500),
//      ("aatish", "Standard", 45, 3000),
//      ("animesh", "Premium", 20, 9000),
//      ("nishad", "Basic", 80, 1100)
//    ).toDF("name", "membership", "days_since_last_purchase", "total_purchase_amount")
//
//
//  }
//
//
//}
