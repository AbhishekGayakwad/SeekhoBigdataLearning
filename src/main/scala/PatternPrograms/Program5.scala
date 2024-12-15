package PatternPrograms

object Program5 {


  def main(args:Array[String]): Unit = {
//
//    5.Diamond Pattern
//        *
//       ***
//      *****
//     *******
//      *****
//       ***
//        *
val string = "*"
    var l = 4
    var i  = 1
    var j = 0
    println("first method")
    for (i<- 1  to l) {
      val space = " "
      println(space * (l - i) + string * (i + j) + space * (l - i))
      j = j + 1
    }


    var li = 1
    var ii  = 3
    var jj = 2
    for (ii<- 3  to li by -1){
      val space= " "
      println(space*(li)+string*(ii+jj)+space*(li))
      jj=jj-1
      li=li+1

    }


  }
}
