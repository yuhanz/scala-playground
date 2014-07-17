def readFile(fileName : String) : String = {
  return scala.io.Source.fromFile(fileName).mkString
}
