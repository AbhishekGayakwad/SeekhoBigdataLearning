package Array1

object ArrayProblem2 {
  def main(args:Array[String]){

    val arr = Array(1,2,3,4,5)

    for (i<-0 until  (arr.length) ){
      if(i==3){
        arr(i)=99
      }

    }
    print(arr.mkString(", "))
  }

}
