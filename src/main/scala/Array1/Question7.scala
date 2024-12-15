package Array1

object Question7 {
  def main(args:Array[String]): Unit = {
    def secondSmallest(arr: Array[Int]): Int = {
      if (arr.length < 2) {
        throw new IllegalArgumentException("Array must contain at least two elements")
      }

      var smallest = Int.MaxValue
      var secondSmallest = Int.MaxValue

      // Iterate through the array to find the smallest and second smallest
      for (num <- arr) {
        if (num < smallest) {
          secondSmallest = smallest
          smallest = num
        } else if (num < secondSmallest && num != smallest) {
          secondSmallest = num
        }
      }

      // If secondSmallest hasn't changed, it means there is no distinct second smallest
      if (secondSmallest == Int.MaxValue) {
        throw new IllegalArgumentException("No distinct second smallest element")
      }

      secondSmallest
    }


    val arr = Array(4, 2, 9, 7, 5)
    val result = secondSmallest(arr)

    println(s"The second smallest element is: $result")


}
  }
