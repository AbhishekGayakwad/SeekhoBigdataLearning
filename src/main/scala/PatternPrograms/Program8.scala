package PatternPrograms

object Program8 {


  def main(args:Array[String]): Unit = {
//
//    6 . Hallow Diamond Pattern

    val string = "*"
    val l = 5
    var i  = 1
    println("first method")
    for (i<- 1  to l){
      for (k <- 1 to i)
        print(k)
      println()
    }


  }
}
