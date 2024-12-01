import org.apache.avro.generic.GenericData.StringType
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.{IntegerType, StructField, StructType}

object data_datavaldations {

  def main(args:Array[String]):Unit= {

    val spark = SparkSession.builder
      .appName("datavalidations")
      .master("local[*]")
      .getOrCreate()


    import spark.implicits._
    val df = List(
      ("Karthik", Some(30), Some("Engineer")),
      ("Priya", Some(25), Some("Scientist")),
      ("Madhav", Some(40), Some("Doctor")),
      ("Mohan", None, Some("Doctor")),
      ("Veena", Some(27), Some("Scientist")),
      ("Veer", Some("karthik"), None)
    ).toDF("name", "age", "profession")

    val invalidDataTypes = df.filter(
      !col("age").cast("int").isNotNull
    )
    invalidDataTypes.show()

    if (invalidDataTypes.count() > 0) {
      println("Invalid data types found.")
    } else {
      println("All columns have valid data types.")

    }
  }}