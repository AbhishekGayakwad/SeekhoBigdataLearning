package Array1

object ArrayProblem14 {
  def main(args:Array[String]){

    val arr = Array(1, 2, 5,5,2,1)

    //reverse the array

    // to reverse the array we need to create a new array and insert the last element of arr in the first place of new array
    // create a new array using Array class of type Int, which will be of same size as the arr
    val newarr = new Array[Int](arr.length)
    //run through the array arr using for loop in reverse order

    for (i<- arr.length-1 to 0 by -1)
      {
        //insert the data into the newarr
        newarr(arr.length-1-i)=arr(i)
      }
      //check the newarr by printing the elements using mkstring
    println(newarr.mkString(" "))

    if(arr.sameElements(newarr)){
      println("True")
    }
    else{
      println("false")
    }



  }

}
