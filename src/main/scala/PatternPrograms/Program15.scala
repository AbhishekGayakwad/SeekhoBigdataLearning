package PatternPrograms

object Program15 {


  def main(args: Array[String]): Unit = {

    val rows = 5
    for (i <- 0 until rows) {
      var value = 1
      // Print leading spaces for formatting
      for (_ <- 0 until (rows - i - 1)) {
        print(" ")
      }

      // Print values in the current row
      for (j <- 0 to i) {
        print(s"$value ")
        value = value * (i - j) / (j + 1)
      }

      println() // Move to the next line after each row
    }
  }
}


//package PatternPrograms
//
//object Program15 {
//
//
//  def main(args: Array[String]): Unit = {
//
//    var n = 5
//    val s = " "
//
//
//    val m = 11
//    var sum = 0
//
//    for (j <- n-1 to 0  by -1)
//    {
//      print(s*j+" ")
//      if (j == 4){
//        sum = 1
//        sum.toString.s
//
//        println(sum+" ")
//      }
//      else{
//        sum = sum * m
//        println(sum)
//      }
//      //print(j)
//    }
//    //    for (i<-1 to 5 ){
//    //
//    //      else{
//    //        sum = sum * m
//    //        println(sum)
//    //      }
//    //
//    //    }
//
//  }
//}
