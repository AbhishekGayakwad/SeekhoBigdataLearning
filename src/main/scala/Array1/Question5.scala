package Array1

object Question5 {
  def main(args:Array[String]): Unit = {

    var arr = Array(11,1, 20, 9, 10)
    var arr1 = Array(111,112, 201, 229, 1110)

    println("array 1:")
    println(arr.mkString(", "))

    println("array 2:")
    println(arr1.mkString(", "))

    var arr3 = new Array[Int](arr.length+arr1.length)

    for (i <- 0 until arr.length){
      //println(arr(i))
      arr3(i)=arr(i)

    }
    for (i <- arr.length until arr.length+arr1.length){
      //print(i)
      //print(i-arr1.length)
      arr3(i)=arr1(i-arr1.length)

    }
    println("Merged array 1 and array2 :")
    println(arr3.mkString(", "))
  }

}
