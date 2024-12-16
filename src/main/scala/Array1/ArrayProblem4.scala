package Array1

object ArrayProblem4 {
  def main(args:Array[String]){

    val arr = Array(1,2,3,4,5)
    val list = new Array[Int](5)
    var sum = 0
    for (i<-arr.length-1  to 0 by -1){
      list(arr.length-1-i)=arr(i)
      
    }
    print(list.mkString(" "))
  }

}
