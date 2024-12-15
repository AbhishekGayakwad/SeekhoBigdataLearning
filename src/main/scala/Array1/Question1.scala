package Array1



object Question1 {
  def main(args:Array[String]): Unit = {

    var arr = new Array[Int](5)
    val size = arr.length
    for (i <- 0 until size  ){
      //rint(i)
      arr(i) = i+1
      println(arr(i))
    }

  }

}
