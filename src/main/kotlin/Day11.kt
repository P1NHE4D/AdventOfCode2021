import java.io.File

class Day11 : Day {
    private var cavern = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day11.txt")
        .inputStream()
        .bufferedReader()
        .use { it.readLines() }
        .map { str -> str.toCharArray().map { char -> char.digitToInt() }.toMutableList() }

    override fun task01() {
        simulate(100).also {
            cavern.forEach { println(it.joinToString("")) }
            println("Task 01 solution: $it")
        }
    }

    override fun task02() {
        findSynchronousFlash().also {
            println("Task 02 solution: $it")
        }
    }

    private fun findSynchronousFlash(days: Int = 0) : Int {
        if (cavern.fold(0) {acc, row -> acc + row.sum()} == 0) {
            cavern.forEach { println(it.joinToString("")) }
            return days
        }
        simulateDay()
        return findSynchronousFlash(days + 1)
    }

    private fun simulate(days: Int) : Int {
        if (days <= 0) return 0
        return simulateDay() + simulate(days - 1)
    }

    private fun simulateDay() : Int {
        cavern = cavern.map { it.map { cell -> cell + 1 }.toMutableList() }
        return cavern.mapIndexed { rowIndex, row ->
            row.foldIndexed(0) { colIndex, acc, col ->
                if (col > 9) acc + applyFlash(rowIndex, colIndex)
                else acc
            }
        }.sum()
    }

    private fun applyFlash(row: Int, col: Int) : Int {
        if (cavern.getOrNull(row)?.getOrNull(col) == null) return 0
        if (cavern[row][col] == 0) return 0
        cavern[row][col] += 1
        if (cavern[row][col] > 9) {
            cavern[row][col] = 0
            return 1 + applyFlash(row - 1, col - 1) +
                    applyFlash(row - 1, col) +
                    applyFlash(row - 1, col + 1) +
                    applyFlash(row, col - 1) +
                    applyFlash(row, col + 1) +
                    applyFlash(row + 1, col - 1) +
                    applyFlash(row + 1, col) +
                    applyFlash(row + 1, col + 1)
        }
        return 0
    }
}