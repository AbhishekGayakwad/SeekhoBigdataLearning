package PatternPrograms

object Program12 {


  def main(args: Array[String]): Unit = {
    //
    //    6 . Hallow Diamond Pattern

    println("first method")
    val space =  " "
    val s = "*"
    val t = 5

    for (i <- 0 to 4){
      if(i == 0){
        println(space*(t-i)+s)
      }
      else{
        println(space*(t-i)+s+space*(i*2)+s)
      }
    }

  }
}
