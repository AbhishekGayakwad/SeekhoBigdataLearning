package Rdd

import org.apache.spark.{SparkConf, SparkContext}

object WordCount extends App {

  val conf = new SparkConf().setAppName("SparkRDDExample").setMaster("local")
  val sc = new SparkContext(conf)

  val rdd1= sc.textFile("C:\\Users\\Admin\\Desktop\\wordcount.txt")
  val rdd2 = rdd1.flatMap(line => line.split(" ")).map(word => (word.toLowerCase,1))
  .reduceByKey((x,y) => (x+y)).map( x => (x._2,x._1)).sortByKey().map(x=>(x._1,x._2))

  val result = rdd2.collect()


  // 5. Print the result
  result.foreach(println)


}
