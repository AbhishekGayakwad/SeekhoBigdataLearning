package Array1

object ArrayProblem9 {
  def main(args:Array[String]){

    val arr = Array(3,7,1,9,5)
    var max=0

    for (i<-0 until  (arr.length) ){
      if(arr(i)>max){
        max=arr(i)
      }
    }

    var min=max
    for (i<-0 until  (arr.length) ){
      if(arr(i)<min){
        min=arr(i)
      }
    }
    print(s"Max:$max and Min $min")

  }

}
