  package DateManipulation

import org.apache.log4j.Logger
import org.apache.spark.sql.{Column, DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, date_add, date_format, dayofweek, input_file_name, udf, when}

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

  object Question25 {
    def main(args:Array[String]): Unit = {

      val logger = Logger.getLogger(this.getClass)
//      Calculate the date 30 business days after a given date.
//      //        o Sample Data:
//      //       Scala: Seq(("2024-01-01"), ("2024-02-10")).toDF("date")
//

      val spark = SparkSession.builder()
        .appName("question13")
        .master("local[*]")
        .getOrCreate()

      import spark.implicits._
      val df = Seq(("2024-01-01")).toDF("date")

      val dateToIncrease = 30

      def getWeek(c:String):String={
        val d = c.toInt
        d match {
          case 1 => return "Sunday"
          case 2 => return "Monday"
          case 3 => return "Tuesday"
          case 4 => return "Wednesday"
          case 5 => return "Thursday"
          case 6 => return "Friday"
          case 7 => return "Saturday"
          case _ => return "Invalid day"
        }
      }

      def getNumberofBusDay1(dateToIncrease:Int,x:Date):Date= {
        val cal = Calendar.getInstance()
        cal.setTime(x)
        cal.add(Calendar.DAY_OF_YEAR, dateToIncrease)
        cal.getTime
      }

      def isWeekend(date: Date): Boolean = {
        val calendar = Calendar.getInstance()
        calendar.setTime(date)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY
      }
      def addBusinessDays(startDate: String, daysToAdd: Int): String = {
        val format = new SimpleDateFormat("yyyy-MM-dd")
        var currentDate = format.parse(startDate)
        var addedDays = 0

        while (addedDays < daysToAdd) {
          currentDate = getNumberofBusDay1(1,currentDate)
          if (!isWeekend(currentDate)) addedDays += 1
        }
        format.format(currentDate)
      }
      val addBusinessDaysUDF = udf((date: String) => addBusinessDays(date, 30))

//      def getNumberofBusDay(dateToIncrease:Int,x:DataFrame):Unit= {
//        var k =0
//        var f = 0
//
//        while (k<=dateToIncrease) {
//          val weekdf= x.select(dayofweek(date_add(x.col("date"),k)))
//          val week = weekdf.collect()
//          //week.foreach(r=>println(getWeek(r.mkString)))
//
//          week.foreach{r=>
//            //print(getWeek(r.mkString))
//            if(getWeek(r.mkString)== "Sunday" || getWeek(r.mkString)=="Saturday") {
//              //print(getWeek(r.mkString),"in the if")
//              f=f+1
//             }
//            k=k+1
//          }
//
//        }
//        println(f+dateToIncrease)
//      }
//      getNumberofBusDay(30,df)

         // Apply the UDF to the DataFrame
      val result = df.withColumn("business_day_30_after", addBusinessDaysUDF(col("date")))

      // Show the result
      result.show()


      }

    }