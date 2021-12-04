import java.io.File

class Day1 : Day {
    private val data = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day1.txt")
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
            if (data[i] > data[i - 1]) increasingCount += 1
        }
        println("Task 1 solution: $increasingCount")
    }

    override fun task02() {
        var increasingCount = 0
        for (i in 3..data.lastIndex) {
            val prevWindow = data[i - 3] + data[i - 2] + data[i - 1]
            val currWindow = data[i - 2] + data[i - 1] + data[i]
            if (currWindow > prevWindow) increasingCount += 1
        }
        println("Task 2 solution: $increasingCount")
    }

}