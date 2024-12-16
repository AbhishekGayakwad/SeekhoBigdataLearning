package Array1

object ArrayProblem3 {
  def main(args:Array[String]){

    val arr = Array(5,10,15,20,25)
    var sum = 0
    for (i<-0 until  (arr.length) ){
     sum= sum+arr(i)
      
    }
    print(sum)
  }

}
