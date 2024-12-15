package Array1

object Question3 {
  def main(args:Array[String]): Unit = {

    var arr = Array("apple","banana", "cherry", "date", "elderberry")
    println(arr.mkString(", "))
   var arr1 = new Array[String](arr.length)
    //println(arr(1))
    val size = arr.length
    for (i <- size-1  to  0 by -1 ){

          arr1(size-1 - i) = arr(i)
    }

    println(arr1.mkString(", "))


  }

}
