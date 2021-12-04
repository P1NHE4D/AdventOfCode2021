import java.io.File

class Day2 : Day {
    private val data = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day2.txt")
        .inputStream()
        .bufferedReader()
        .use {
            it.readLines()
        }
        .map {
            it.split(" ")
        }

    override fun task01() {
        var horizontalPosition = 0
        var depth = 0
        data.forEach { command ->
            val action = command[0]
            val value = command[1].toInt()
            when(action) {
                "forward" -> horizontalPosition += value
                "down" -> depth += value
                "up" -> depth -= value
            }
        }
        println("Task 01 solution: ${horizontalPosition * depth}")
    }

    override fun task02() {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0
        data.forEach { command ->
            val action = command[0]
            val value = command[1].toInt()
            when(action) {
                "forward" -> {
                    horizontalPosition += value
                    depth += aim * value
                }
                "down" -> aim += value
                "up" -> aim -= value
            }
        }
        println("Task 02 solution: ${horizontalPosition * depth}")
    }

}