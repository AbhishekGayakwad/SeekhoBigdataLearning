package Array1

object ArrayProblem5 {
  def main(args:Array[String]){
    val s = 15
    val arr = Array(10, 15, 20, 25, 30)
    val list = new Array[Int](5)
    var sum = 0
    for (i<-0 until arr.length) {
      if(arr(i)==s){
        println("number exists")
      }
      
    }
  }

}
