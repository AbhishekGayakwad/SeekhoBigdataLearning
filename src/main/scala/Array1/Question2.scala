package Array1

object Question2 {
  def main(args:Array[String]): Unit = {
    def sum1(array: Array[Int]): Unit = {
      var s = array.length
      var r = 0
      for (i <- 0 until s  ){
        //print(array(i))
        r= r + array(i)

      }
      print(r)
    }
    var arr = new Array[Int](5)
    val size = arr.length
    for (i <- 0 until size  ){
      //rint(i)
      arr(i) = i+1

    }
//c
//    sum1(arr)



  }

}
