import scala.io.Source
import scala.util.Try

object MaxTriangle {

  //The algorithm is:
  //Iterate through the triangle from the top and calculate the max
  //possible value at each place based on the max of the two possible numbers above it.
  //Because it is a triangle, the numbers above will either have the same index or index-1
  //Pick the max of these two and add it to the current
  //When the end is reached, the max of the last line will hold the value of the maximal path
  //The path can be reconstructed if we record the intermediate lines and follow the max
  //values at each level

  def calculateLine(previous: Array[Int], current: Array[Int]): Array[Int] = {
    def addMaxFromPrevious(value: Int, index: Int) = {
      val maxPrevious = List(Try(previous(index)).toOption, Try(previous(index-1)).toOption).flatten.max
      maxPrevious + value
    }
    current.zipWithIndex.map{case (v, i) => addMaxFromPrevious(v, i)}
  }

  def main(args: Array[String]) {
    val triangleLines = Source.fromFile("triangle.txt").getLines.map(_.split(' ').map(_.toInt))
    val lastLine = triangleLines.foldLeft(Array(0))(calculateLine)
    println(lastLine.max)
  }
}
