import java.io.File

class Day4 : Day {
    private val bingoBoards = mutableListOf<BingoBoard>()
    private val bingoNumbers: List<Int>

    init {
        val data = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day4.txt")
            .inputStream()
            .bufferedReader()
            .use {
                it.readLines()
            }
        bingoNumbers = data[0].split(',').map { it.toInt() }
        var boardValues = mutableListOf<List<Int>>()
        data.drop(2)
            .forEach {
                if (it != "") {
                    val line = it.replace("\n", "").split(" ").filter { it != "" }.map { str -> str.trim().toInt() }
                    boardValues.add(line)
                } else {
                    bingoBoards.add(BingoBoard(boardValues))
                    boardValues = mutableListOf()
                }
            }
    }

    override fun task01() {
        bingoNumbers.forEach { num ->
            bingoBoards.forEach { board ->
                board.markNumber(num)
                if (board.bingo()) {
                    println("Task 01 solution: ${board.sumOfUnmarkedNumbers() * num}")
                    return@task01
                }
            }
        }
    }

    override fun task02() {
        var boards = bingoBoards
        var lastScore = 0
        bingoNumbers.forEach { num ->
            boards.forEach { board ->
                board.markNumber(num)
                if (board.bingo()) {
                    lastScore = board.sumOfUnmarkedNumbers() * num
                }
            }
            boards = boards.filter { !it.bingo() }.toMutableList()
            if (boards.size == 0) return@forEach
        }
        println("Task 02 solution: $lastScore")
    }

}

data class BingoNumber(
    val value: Int,
    var marked: Boolean
)

class BingoBoard(
    rawNumbers: List<List<Int>>
) {
    private val board: List<List<BingoNumber>> = rawNumbers.map {
        it.map { rawNumber ->
            BingoNumber(rawNumber, false)
        }
    }

    fun markNumber(number: Int) {
        for (row in board) {
            for (bingoNumber in row) {
                if (bingoNumber.value == number) {
                    bingoNumber.marked = true
                }
            }
        }
    }

    fun bingo(): Boolean {
        var bingo: Boolean
        for (row in board) {
            bingo = row.all { bingoNumber -> bingoNumber.marked }
            if (bingo) {
                return true
            }
        }
        for (row in board.transpose()) {
            bingo = row.all { bingoNumber -> bingoNumber.marked }
            if (bingo) {
                return true
            }
        }
        return false
    }

    fun sumOfUnmarkedNumbers(): Int = board.fold(0) { acc, list ->
        acc + list.filter { !it.marked }.fold(0) { rowSum, bingoNumber -> rowSum + bingoNumber.value }
    }
}


