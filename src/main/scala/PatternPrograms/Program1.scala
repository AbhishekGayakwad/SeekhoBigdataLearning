package PatternPrograms

object Program1 {

  def main(args:Array[String]): Unit = {

//    Right-Angle Triangle Pattern
//    *
//    **
//    ***
//    ****
//    *****


    val string = "*"
    val l = 5
    var i  = 1
    println("first method")
    for (i<- 1  to l){
      println(string*i)

    }
    println("second method")
    while ( i <= l){
      println(string*i)
      i=i+1

    }

  }

}
