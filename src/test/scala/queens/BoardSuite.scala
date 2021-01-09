package queens

import munit.FunSuite

class BoardSuite extends FunSuite:

  val empty8x8 = Board.make(8)

  test("empty") {
    assert(empty8x8.empty(0, 0))
    assert(!empty8x8.placeQueen(0, 0).empty(0, 0))
    assert(empty8x8.placeQueen(0, 0).empty(1, 0))
  }

  test("queenCount") {
    assertEquals(empty8x8.queenCount, 0)
    assertEquals(empty8x8.placeQueen(0, 0).queenCount, 1)
    assertEquals(empty8x8.placeQueen(0, 0).placeQueen(1, 1).queenCount, 2)
  }

  test("valid - empty 8x8 board is valid") {
    assert(empty8x8.valid)
  }

  test("valid - column conflicts") {
    assert(!empty8x8.placeQueen(0, 0).placeQueen(0, 2).valid)
  }

  test("valid - row conflicts") {
    assert(!empty8x8.placeQueen(0, 0).placeQueen(2, 0).valid)
  }

  test("valid - diagonal conflicts") {
    assert(!empty8x8.placeQueen(0, 0).placeQueen(2, 2).valid)
    assert(!empty8x8.placeQueen(0, 1).placeQueen(1, 0).valid)
  }

  test("solutions - empty 8x8 - all solutions are valid") {
    val solutions = empty8x8.solutions
    assert(solutions.forall(_.valid))
  }

  test("solutions - empty 8x8 - all queens are placed") {
    val solutions = empty8x8.solutions
    assert(solutions.forall(_.queenCount == 8))
  }

  test("solutions - empty 8x8 - finds all solutions") {
    val solutions = empty8x8.solutions
    assertEquals(solutions.size, 92)
  }

  def placeQueens(start: Board, positions: (Int, Int)*): Board =
    positions.foldLeft(start) { case (acc, (col, row)) => acc.placeQueen(col, row) }

  test("solutions - 8x8 with A1 - finds all solutions") {
    val start = empty8x8.placeQueen(0, 0)
    val solutions = start.solutions
    assertEquals(solutions.size, 4)
    val first = placeQueens(start, (1, 4), (2, 7), (3, 5), (4, 2), (5, 6), (6, 1), (7, 3))
    val second = placeQueens(start, (1, 5), (2, 7), (3, 2), (4, 6), (5, 3), (6, 1), (7, 4))
    val third = placeQueens(start, (1, 6), (2, 3), (3, 5), (4, 7), (5, 1), (6, 4), (7, 2))
    val fourth = placeQueens(start, (1, 6), (2, 4), (3, 7), (4, 1), (5, 3), (6, 5), (7, 2))
    assertEquals(solutions.toSet, Set(first, second, third, fourth))
  }

end BoardSuite
