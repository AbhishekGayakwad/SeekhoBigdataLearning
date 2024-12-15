package PatternPrograms

object Program10 {


  def main(args:Array[String]): Unit = {
//
//    6 . Hallow Diamond Pattern

    val string = "*"
    val space = " "
    val l = 5
    var k = 0
    println("first method")
    for (i <- 1 to 5 ){
      if (i == 1 ){
        println(string*(i))
      }

      if (i == 5 ){
        println(string*(i))
      } else
      {
        println(string+space*(k)+string)
        k= k+1
      }


    }





  }
}
