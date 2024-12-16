package Array1

object ArrayProblem11 {
  def main(args:Array[String]){


    var l = List[Int]()
    val arr = Array(9,3,5,1,7,8)
    for (i<-0 until arr.length){
      if(arr(i)%2==0){
        l=l:+arr(i)
      }
    }

var s = l.toArray
println(s.mkString(" "))

  }

}
