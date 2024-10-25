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
      println("hello World from screen 2")


    var array = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    array.foreach(println)
    println(array(0))
    val len = array.length
    println(s"the length of array is " + len)
    for (i <- 0 until len) {
      println(array(i))
    }
  }
}
