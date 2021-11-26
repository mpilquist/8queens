package queens

@main def EightQueens =
  val board = Board.make(8, 8)
  val solutions = board.solutions
  println(s"Found ${solutions.size} solutions...")
  for (s, idx) <- solutions.zipWithIndex do
    println("")
    println(s"Solution $idx:")
    println(s)