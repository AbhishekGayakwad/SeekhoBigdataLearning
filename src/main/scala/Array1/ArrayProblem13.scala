package Array1

object ArrayProblem13 {
  def main(args:Array[String]){

    var count=0
    val nu = 3
    val arr = Array(1, 2, 3)
    val arr1 = Array(4, 5, 6)
    var l = new Array[Int](arr.length+arr1.length)
    for (i<-0 until arr.length){
      l(i)=arr(i)
    }
    for (i <- arr.length until arr.length+arr1.length){
      l(i)=arr1(i-arr1.length)
    }


println(l.mkString(" "))

  }

}
