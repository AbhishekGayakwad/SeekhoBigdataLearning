package Array1

object Question6 {
  def main(args:Array[String]): Unit = {

    var arr = Array(11,1, 20, 9, 10)



    def avg1(array: Array[Int]): Unit = {
      var sum = 0
      var avg = 0
      for (i <- 0 until array.length){
        //println(arr(i))
        sum=sum+array(i)

      }

      avg = sum/array.length
      print(avg)
    }
    avg1(arr)

  }

}
