package PatternPrograms

object Program16 {


  def main(args: Array[String]): Unit = {

    val rows = 15
    var count = rows
    for (i<- 1 to rows){
      for (j<- 1 to rows){
        if(i==j || j==count ){
          print("*")

        }
          print(" ")


//        else{
//          print(" ")
//        }

        //print(" ")

    }
      count = count-1
      //count = rows
      println()
  }
}
}

