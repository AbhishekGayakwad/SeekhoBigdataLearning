package PatternPrograms

object Program4 {


  def main(args:Array[String]): Unit = {
//
//    4.Inverted Pyramid
//  *********
//   *******
//    *****
//     ***
//      *

    val string = "*"
    var l = 0
    var i  = 5
    var j = 4
    println("first method")
    for (i<- 5  to l by -1){
      val space= " "
      println(space*(l)+string*(i+j)+space*(l))
      j=j-1
      l=l+1

    }


  }
}
