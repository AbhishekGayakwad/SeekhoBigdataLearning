package Array1

import scala.sys.exit

object Question10 {
  def main(args:Array[String]): Unit = {
    def secondSmallest(arr: Array[Int]): Unit = {
      if (arr.length < 2) {
        throw new IllegalArgumentException("Array must contain at least two elements")
      }
      var smallest = arr(0)

      // Iterate through the array to find the smallest and second smallest
      for (num <- arr) {
        if (num == smallest) {
          smallest = num
        }
        else if(num>smallest){
          smallest = num
        }
        else{
          println("Array not sorted")
          exit(0)
       }
      }
      println("Array is sorted")

    }
    //println("Array is sorted")

    val arr = Array(1,2,5,7,0,9)
    secondSmallest(arr)


  }

}
