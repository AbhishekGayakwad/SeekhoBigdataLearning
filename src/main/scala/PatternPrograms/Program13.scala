package PatternPrograms

object Program13 {


  def main(args: Array[String]): Unit = {
    //
    //    6 . Hallow Diamond Pattern

    val space= " "
    val string = "*"
    var n = 4
    println("first method")
  for (i <- 0 until n){
    for (j <- 0 until i){
      print(" ")
    }
    for (k <- 0 until (2 * (n-i) -1)){
      print("*")
    }
    println()
  }

   for (i <- 1 until n ){
     for (j <- 0 until n-i-1 ) {
       print(" ")
     }

     for (j <- 0 until (2*i+1)){
        print("*")
     }
     println()


   }


//    val ll = 5
//    var ii  = 1
//    var jj = 0
//    for (ii<- 3  to ll){
//      println(space*(ll-ii)+string*(ii+jj)+space*(ll-ii))
//      jj=jj+1
//
//    }

  }
}
