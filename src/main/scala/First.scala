object First {
  def main(args:Array[String]):Unit={
    println("Hello World")

    val a = 5
    var b = 6
    println(s"Check if the a "+a+" is equal to b "+b)
    println(a)
    println(b)
    if(a == b)
    {
      println("Equal")
    }
    else
    {
      println("Not Equal")
    }
    for (i <- 1 to 10 by 2) {
      println(i)
    }
    for (i <- 1 until 10 by 2) {
      println(i)
    }

    var z = 1
    while (z <= 10) {
      println(z)
      z = z + 1

    }


  }
}
