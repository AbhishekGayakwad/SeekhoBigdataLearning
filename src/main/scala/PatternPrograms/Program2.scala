package PatternPrograms

object Program2 {


  def main(args:Array[String]): Unit = {
//
//    Inverted Right-Angle Triangle
//    *****
//    ****
//    ***
//    **
//    *

    val string = "*"
    val l = 1
    var i  = 5
    println("first method")
    for (i<- 5  to l by -1){
      println(string*i)

    }
    println("second method")
    while ( i != 0){
      println(string*i)
      i=i-1

    }


  }
}
