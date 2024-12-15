package Array1

object Question4 {
  def main(args:Array[String]): Unit = {

    var arr = Array(11,1, 20, 9, 10)
    println(arr.mkString(", "))

    val size = arr.length
    var max = 0
    for (i <- 0  to  size-1){
      if(arr(i)>max){
        max=arr(i)
      }

    }


    println(max)


  }

}
