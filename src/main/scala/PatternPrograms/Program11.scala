package PatternPrograms

object Program11 {


  def main(args: Array[String]): Unit = {
    //
    //    6 . Hallow Diamond Pattern

    println("first method")
    for (i <- 0 to 3) {
      var count1 = 1
      for (h <- 0 to i) {
        count1 = count1 + h
      }

      var j = 0
      while (j <= i) {
        print(count1 + j)
        j = j + 1

      }
      println()

    }


  }
}
