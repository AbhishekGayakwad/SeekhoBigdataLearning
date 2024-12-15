package PatternPrograms

object Program7 {


  def main(args:Array[String]): Unit = {
//
//    6 . Hallow Diamond Pattern

    val string = "*"
    val space = " "
    var l = 5
    var j = 7
    var k = 0
    println("first method")
    for (i<- 1  to l) {
      if (i == 5){
        println(string*(i)+space*(j)+string*(i-1))
      }
      else {
        println(string * (i) + space * (j) + string * (i))

        j = j - 2
      }
    }
    var ll = 4
    var jj= 1
    var kk = 0
for(i<- 4  to 1 by -1)
  {
    println(string*(i)+space*(jj)+string*(i))
    jj = jj + 2
  }


  }
}
