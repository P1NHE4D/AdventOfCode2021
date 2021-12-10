import java.io.File

class Day9 : Day {
    private val heightMap = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day9.txt")
        .inputStream()
        .bufferedReader()
        .use { it.readLines() }
        .map { it.toCharArray().map { char -> char.digitToInt() } }
    private var lowPoints: MutableList<List<Int>> = mutableListOf()

    override fun task01() {
        heightMap.flatMapIndexed { rowIndex, row ->
            row.filterIndexed { colIndex, col ->
                listOf(
                    heightMap.getOrNull(rowIndex - 1)?.getOrNull(colIndex) ?: 10,
                    heightMap.getOrNull(rowIndex)?.getOrNull(colIndex + 1) ?: 10,
                    heightMap.getOrNull(rowIndex + 1)?.getOrNull(colIndex) ?: 10,
                    heightMap.getOrNull(rowIndex)?.getOrNull(colIndex - 1) ?: 10
                ).all { it > col }.also {
                    if (it) lowPoints.add(listOf(rowIndex, colIndex))
                }
            }
        }.fold(0) { acc, i ->
            acc + i + 1
        }.also { println("Task 01 solution: $it") }
    }

    override fun task02() {
        lowPoints
            .map { computeBasinSize(it[0], it[1], mutableSetOf()) }
            .sortedDescending()
            .take(3)
            .reduce { a, b -> a * b }
            .also { println("Task 02 solution: $it") }
    }

    private fun computeBasinSize(row: Int, col: Int, visited: MutableSet<List<Int>>): Int {
        val posValue = heightMap.getOrNull(row)?.getOrNull(col) ?: return 0
        if (posValue == 9 || visited.contains(listOf(row, col))) return 0
        visited.add(listOf(row, col))
        return computeBasinSize(row - 1, col, visited) + computeBasinSize(row, col - 1, visited) + computeBasinSize(row + 1, col, visited) + computeBasinSize(row, col + 1, visited) + 1
    }
}