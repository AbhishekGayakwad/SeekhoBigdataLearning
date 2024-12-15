package Array1

import scala.sys.exit

object Question9 {
  def main(args:Array[String]): Unit = {


    var arr = Array(3,9,8,6,5,2)

    var arr1= List[Int]()
    for (i <- 0 until arr.length){
      if(arr(i)%2!=0){
        arr1=arr1:+arr(i)
      }
    }
    val f = arr1.toArray
    println(f.mkString(" "))
  }

}
