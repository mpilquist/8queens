package queens

object EightQueens extends App:
  val board = Board.make(8, 8)
  val solutions = board.solutions
  println(s"Found ${solutions.size} solutions...")
  solutions.zipWithIndex.foreach { (s, idx) =>
    println("")
    println(s"Solution $idx:")
    println(s)
  }
