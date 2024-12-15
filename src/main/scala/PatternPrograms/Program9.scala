package PatternPrograms

object Program9 {


  def main(args:Array[String]): Unit = {
//
//    6 . Hallow Diamond Pattern

    val string = "*"
    val l = 5
    var i  = 1
    println("first method")
    for (i<- 5  to 1 by - 1){
      for (k <- 1 to i)
        print(k)
      println()
    }


  }
}
