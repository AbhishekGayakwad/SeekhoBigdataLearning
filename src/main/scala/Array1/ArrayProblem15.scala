package Array1

object ArrayProblem15 {
  def main(args:Array[String]){

    val arr = Array(1, 2, 3, 4)
    val arr1 = new Array[Int](arr.length)
    //expected output : (2,3,4,1)
    for(i<- arr.length-1 to 0  by -1){
      if(i==0){
        arr1(arr1.length-1)=arr(i)
      }
      else{
        arr1(i-1)=arr(i)
      }

    }

    println(arr1.mkString(" "))






  }

}
