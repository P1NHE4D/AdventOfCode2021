import java.io.File

class Day2 : Day {
    val data = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day2.txt")
        .inputStream()
        .bufferedReader()
        .use {
            it.readLines()
        }
        .map {
            it.split(" ")
        }

    override fun task01() {
        var horizontal_position = 0
        var depth = 0
        for (command in data) {
            val action = command[0]
            val value = command[1].toInt()
            when(action) {
                "forward" -> horizontal_position += value
                "down" -> depth += value
                "up" -> depth -= value
            }
        }
        println("Task 01 solution: ${horizontal_position * depth}")
    }

    override fun task02() {
        var horizontal_position = 0
        var depth = 0
        var aim = 0
        for (command in data) {
            val action = command[0]
            val value = command[1].toInt()
            when(action) {
                "forward" -> {
                    horizontal_position += value
                    depth += aim * value
                }
                "down" -> aim += value
                "up" -> aim -= value
            }
        }
        println("Task 02 solution: ${horizontal_position * depth}")
    }

}