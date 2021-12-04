import java.io.File

class Day1 : Day {
    val data = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day1.txt")
        .inputStream()
        .bufferedReader()
        .use {
            it.readLines()
        }
        .map {
            it.replace("\n", "")
            it.toInt()
        }

    override fun task01() {
        var increasingCount = 0
        for (i in 1..data.lastIndex) {
            val curr = data[i]
            val prev = data[i - 1]
            if (curr > prev) increasingCount += 1
        }
        println("Task 1 solution: $increasingCount")
    }

    override fun task02() {
        var increasingCount = 0
        for (i in 3..data.lastIndex) {
            val prev_window = data[i - 3] + data[i - 2] + data[i - 1]
            val curr_window = data[i - 2] + data[i - 1] + data[i]
            if (curr_window > prev_window) increasingCount += 1
        }
        println("Task 2 solution: $increasingCount")
    }

}