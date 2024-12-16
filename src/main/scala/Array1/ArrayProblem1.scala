package Array1

object ArrayProblem1 {
  def main(args:Array[String]){

    val arr = new Array[Int](5)

    for (i<-0 until  (arr.length) ){
      //println(i)
      arr(i)=(i+1)*10
    }
    print(arr.mkString(", "))
  }

}
