import java.io.File

class Day6 : Day {
    private var aquarium = LongArray(10) { 0L }.toMutableList()
    private val data = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day6.txt")
        .inputStream()
        .bufferedReader()
        .use { it.readLine() }
        .split(",")
        .map { it.toInt() }


    override fun task01() {
        data.groupingBy { it }.eachCount().forEach { aquarium[it.key] = it.value.toLong() }
        simulate(80)
        val count = aquarium.fold(0L) {acc, i -> acc + i }
        println("Task 01 solution: $count")
    }

    override fun task02() {
        aquarium = LongArray(10) { 0L }.toMutableList()
        data.groupingBy { it }.eachCount().forEach { aquarium[it.key] = it.value.toLong() }
        simulate(256)
        val count = aquarium.fold(0L) {acc, i -> acc + i }
        println("Task 02 solution: $count")
    }

    private fun simulate(days: Int) {
        aquarium[9] += aquarium[0]
        aquarium[7] += aquarium[0]
        aquarium.removeAt(0)
        aquarium.add(9, 0)
        if (days - 1 > 0) simulate(days - 1)
    }
}
