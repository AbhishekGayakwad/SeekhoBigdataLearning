package PatternPrograms

object Program17 {


  def main(args: Array[String]): Unit = {

    val rows = 4
    val cols = rows*4
    for (i <- 1 to rows ){
      for(j <- 1 to cols ){
//        if ((i==1 && j==16) || (i==1 && j==10) || (i==1 && j==4))   // Zig-Zag logic: Diagonals
//          print("*")
//        else if ((i==2 && j==3) || (i==2 && j==5) || (i==2 && j==9) || (i==2 && j==11) || (i==2 && j==15) )   // Zig-Zag logic: Diagonals
//          print("*")
//        else if ((i==3 && j==2) || (i==3 && j==6) || (i==3 && j==8) || (i==3 && j==12) || (i==3 && j==14 ) )  // Zig-Zag logic: Diagonals
//          print("*")
//        else if ((i==4 && j==1) || (i==4 && j==7) || (i==4 && j==13) )  // Zig-Zag logic: Diagonals
//          print("*")
//        else
//          print(" ")
        print(i," ",j," ", j %  i )
      }
      println() // Move to the next line after each row


      }
    }

}


