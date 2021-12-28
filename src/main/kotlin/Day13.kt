import java.io.File

class Day13 : Day {
    private val points = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day13.txt")
        .inputStream()
        .bufferedReader()
        .use { it.readLines() }
        .filter { !((it == "") || (it.startsWith("fold"))) }
        .map { it.split(",").map { str -> str.toInt() } }

    private val instructions = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day13.txt")
        .inputStream()
        .bufferedReader()
        .use { it.readLines() }
        .filter { it.startsWith("fold") }
        .map { it.removePrefix("fold along ").split("=") }
        .map {
            if (it[0] == "x") listOf(it[1].toInt(), 0) else listOf(0, it[1].toInt())
        }

    override fun task01() {
        val xSize = points.map { l -> l[0] }.maxOrNull() ?.plus(1) ?: 10
        val ySize = points.map { l -> l[1] }.maxOrNull() ?.plus(1) ?: 10
        val dotMap = points.fold(Array(ySize) {IntArray(xSize) {0} }) { acc, point ->
            acc[point[1]][point[0]] = 1
            acc
        }.map { it.toList() }.toMutableList()

        val foldedDotMap = if (instructions[0][0] == 0) dotMap.foldUp(instructions[0][1]) else dotMap.transpose().toMutableList().foldUp(instructions[0][0]).transpose()
        foldedDotMap.flatten().filter { it == 1 }.sum().also { println(it) }
    }

    override fun task02() {
        val xSize = points.map { l -> l[0] }.maxOrNull() ?.plus(1) ?: 10
        val ySize = points.map { l -> l[1] }.maxOrNull() ?.plus(1) ?: 10
        val dotMap = points.fold(Array(ySize) {IntArray(xSize) {0} }) { acc, point ->
            acc[point[1]][point[0]] = 1
            acc
        }.map { it.toList() }.toMutableList()

        instructions.fold(dotMap) {acc, instruction ->
            if (instruction[0] == 0) {
                acc.foldUp(instruction[1])
            } else {
                acc.transpose().toMutableList().foldUp(instruction[0]).transpose().toMutableList()
            }
        }.map {
            it.map {int -> if (int == 0) '.' else '#'  }
        }.onEach { line ->
            println(line.joinToString(""))
        }
    }

    private fun MutableList<List<Int>>.foldUp(start: Int, lower: Int = start, upper: Int = start) : MutableList<List<Int>> {
        if (!(this.indices.contains(lower) && this.indices.contains(upper))) return this.subList(0, start)
        this[lower] = this[lower].zip(this[upper]).map { it.first or it.second }
        return foldUp( start, lower - 1, upper + 1)
    }
}