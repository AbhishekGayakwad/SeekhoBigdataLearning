package PatternPrograms

object Program14 {


  def main(args: Array[String]): Unit = {
    //
    //    6 . Hallow Diamond Pattern

    val space = " "
    val string = "*"
    var n = 5
    println("first method")
    for (i <- 1 until n + 1) {
      if (i % 2 == 0) {
        for (j <- i until i + 1) {

          if (j % 2 == 0) {
            print((string * j))
          }

        }

      }
      else {
        for (k <- 1 to i) {
          print(k)
        }
      }

    }

  }
}
