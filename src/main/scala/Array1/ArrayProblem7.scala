package Array1

object ArrayProblem7 {
  def main(args:Array[String]){
    val s = 15
    val arr = Array(Array(1,2,3),Array(4,5,6))

    for (i<- 0 until arr.length) {
        for (j <- 0 until arr(1).length ){
          print(arr(i)(j))
        }
        println()
      }

    }
}
