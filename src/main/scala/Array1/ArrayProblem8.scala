package Array1

object ArrayProblem8 {
  def main(args:Array[String]){

    val arr = Array(2,4,6,8)
    val newarr = new Array[Int](arr.length)
    for (i<-0 until  (arr.length) ){
      //println(i)
      newarr(i)=arr(i)*2
    }
    print(newarr.mkString(", "))
  }

}
