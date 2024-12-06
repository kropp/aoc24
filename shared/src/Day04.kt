package day04

import kotlin.jvm.JvmInline

@JvmInline
value class Board(val board: Array<CharArray>) {
    operator fun get(i: Int, j: Int): Char {
        if (i in board.indices) {
            val line = board[i]
            if (j in line.indices) return line[j]
        }
        return '.'
    }
}

fun xmas(board: Board, i: Int, j: Int): Int {
    if (board[i,j] != 'X') return 0
    var result = 0
    fun mas(m: Char, a: Char, s: Char): Boolean = m == 'M' && a == 'A' && s == 'S'

    // horizontal
    if (mas(board[i,j+1], board[i,j+2], board[i,j+3])) result += 1
    if (mas(board[i,j-1], board[i,j-2], board[i,j-3])) result += 1

    // vertical
    if (mas(board[i-1,j], board[i-2,j], board[i-3,j])) result += 1
    if (mas(board[i+1,j], board[i+2,j], board[i+3,j])) result += 1

    // diagonal
    if (mas(board[i-1,j-1], board[i-2,j-2], board[i-3,j-3])) result += 1
    if (mas(board[i-1,j+1], board[i-2,j+2], board[i-3,j+3])) result += 1
    if (mas(board[i+1,j+1], board[i+2,j+2], board[i+3,j+3])) result += 1
    if (mas(board[i+1,j-1], board[i+2,j-2], board[i+3,j-3])) result += 1

    return result
}

fun x_mas(board: Board, i: Int, j: Int): Int {
    val rotations = arrayOf(
        arrayOf(-1, -1,  1, -1,  1,  1, -1,  1),
        arrayOf(-1,  1, -1, -1,  1, -1,  1,  1),
        arrayOf( 1,  1, -1,  1, -1, -1,  1, -1),
        arrayOf( 1, -1,  1,  1, -1,  1, -1, -1),
    )
    if (board[i,j] != 'A') return 0
        if (rotations.any { board[i + it[0], j + it[1]] == 'M' && board[i + it[2], j + it[3]] == 'M' && board[i + it[4], j + it[5]] == 'S' && board[i + it[6], j + it[7]] == 'S' }) return 1
    return 0
}

fun part1(input: String): Int {
    val board = Board(input.lines().map { it.toCharArray() }.toTypedArray())
    var sum = 0
    for (row in board.board.indices) {
        for (col in board.board[row].indices) {
            sum += xmas(board, row, col)
        }
    }
    return sum
}

fun part2(input: String): Int {
    val board = Board(input.lines().map { it.toCharArray() }.toTypedArray())
    var sum = 0
    for (row in board.board.indices) {
        for (col in board.board[row].indices) {
            sum += x_mas(board, row, col)
        }
    }
    return sum
}

//    check(part1("MMMSXXMASM\n" +
//            "MSAMXMSMSA\n" +
//            "AMXSXMAAMM\n" +
//            "MSAMASMSMX\n" +
//            "XMASAMXAMM\n" +
//            "XXAMMXXAMA\n" +
//            "SMSMSASXSS\n" +
//            "SAXAMASAAA\n" +
//            "MAMMMXMMMM\n" +
//            "MXMXAXMASX") == 18)
//    check(part2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))") == 48)

//    val input = readInput("day04")
//    println(part1(input))
//    println(part2(input))
