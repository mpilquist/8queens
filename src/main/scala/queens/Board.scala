package queens

enum Cell:
  case Empty, Queen

case class Board(columns: Vector[Vector[Cell]]):
  def columnCount: Int = columns.size
  def rowCount: Int = columns.head.size

  def cell(columnNumber: Int, rowNumber: Int): Cell =
    columns(columnNumber)(rowNumber)

  def empty(columnNumber: Int, rowNumber: Int): Boolean =
    cell(columnNumber, rowNumber) == Cell.Empty

  def queenCount: Int =
    columns.foldLeft(0)((acc, row) =>
      acc + row.foldLeft(0)((acc, cell) =>
        acc + (if cell == Cell.Queen then 1 else 0)))

  def valid: Boolean =
    columnsValid && rowsValid && diagonalsValid

  private def columnsValid: Boolean =
    ???

  private def rowsValid: Boolean =
    ???
      
  private def diagonalsValid: Boolean =
    ???

  def placeQueen(columnNumber: Int, rowNumber: Int): Board =
    place(columnNumber, rowNumber, Cell.Queen)

  private def place(columnNumber: Int, rowNumber: Int, cell: Cell): Board =
    val updatedRow = columns(columnNumber).updated(rowNumber, cell)
    new Board(columns.updated(columnNumber, updatedRow))
  
  def solutions: Iterable[Board] =
    ???

  override def toString: String =
    ((rowCount - 1) to 0 by -1).toList.map { rowNumber =>
      (0 until columnCount).map { columnNumber =>
        if empty(columnNumber, rowNumber) then "・" else "Q"
      }.mkString
    }.mkString("\n")

end Board

object Board:
  def make(count: Int): Board = make(count, count)
  def make(columns: Int, rows: Int): Board =
    new Board(Vector.fill(columns)(Vector.fill(rows)(Cell.Empty)))

