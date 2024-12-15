package PatternPrograms

object Program3 {


  def main(args:Array[String]): Unit = {
//
//    3.Pyramid Pattern
//      *
//     ***
//    *****
//   *******
//  *********

    val string = "*"
    val l = 5
    var i  = 1
    var j = 0
    println("first method")
    for (i<- 1  to l){
      val space= " "
      println(space*(l-i)+string*(i+j)+space*(l-i))
      j=j+1

    }


  }
}
