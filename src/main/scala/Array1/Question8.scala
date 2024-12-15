package Array1

import scala.sys.exit

object Question8 {
  def main(args:Array[String]): Unit = {
    var str  = "Banana"
    var arr = Array("apple","banana", "cherry", "date", "elderberry")
    println(arr.mkString(", "))
    var flag = 0
    //println(arr(1))
    val size = arr.length
    for (i <- 0  until    arr.length ){
      if (arr(i).toLowerCase == str.toLowerCase){
        println(s"Found the string $str at $i")
        flag=0
        exit(0)
      }
      else{
        flag=1
        //println("Element not found")
      }
    }
    if(flag ==1){
      println("String not found in array")
    }




  }

}
