package Array1

object ArrayProblem12 {
  def main(args:Array[String]){

    var count=0
    val nu = 3
    val arr = Array(1, 3, 3, 5, 3)
    for (i<-0 until arr.length){
      if(arr(i)==nu){
       count=count+1
      }
    }


println(count)

  }

}
