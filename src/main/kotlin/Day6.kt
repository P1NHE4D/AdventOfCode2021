import java.io.File

class Day6 : Day {
    private var aquarium = mutableListOf<Int>()
    private val data = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day6.txt")
        .inputStream()
        .bufferedReader()
        .use { it.readLine() }
        .split(",")
        .map { it.toInt() }

    override fun task01() {
        aquarium = data.toMutableList()
        simulate(80)
        println("Task 01 solution: ${aquarium.size}")
    }

    override fun task02() {
        aquarium = data.toMutableList()
        simulate(256)
        println("Task 02 solution: ${aquarium.size}")
    }

    private fun simulate(days: Int) {
        println(days)
        aquarium = aquarium
            .apply {
                addAll(
                    IntArray(
                        filter { it == 0 }.fold(0) {acc, _ -> acc + 1}
                    ) { 9 }.toList()
                )
            }
            .map { if(it == 0) 6 else it - 1 }
            .toMutableList()
        if (days - 1 > 0) simulate(days - 1)
    }
}
