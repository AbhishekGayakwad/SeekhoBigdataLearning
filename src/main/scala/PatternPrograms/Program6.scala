package PatternPrograms

object Program6 {


  def main(args:Array[String]): Unit = {
//
//    6 . Hallow Diamond Pattern

val string = "*"
    var l = 5
    var i  = 0
    var j = 4
    var k = 0
    println("first method")
    for (i<- 1  to l) {
      val space = " "
      if (i == 1){
        println(space*(j)+string)
        j= j-1
        k=k+1
      }
      else{
        println(space*(j)  + string + space*(k) + string)
        j = j - 1
        k= k +2
      }

    }

    var ll = 1
    var ii  = 3
    var jj = 1
    var kk = 5

    for (ii<- 4  to ll by -1) {
      val space = " "
      if (ii == 1){
        println(space*(jj)+string)
        jj= jj+1
        kk=kk-1
      }
      else{
        println(space*(jj)  + string + space*(kk) + string)
        jj = jj + 1
        kk= kk -2
      }

    }



  }
}
