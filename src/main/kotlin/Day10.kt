import java.io.File

class Day10 : Day {
    private val lines = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day10.txt")
        .inputStream()
        .bufferedReader()
        .use { it.readLines() }

    override fun task01() {
        lines.sumOf {
            lineCorrupted(it)
        }.also {
            println("Task 01 solution: $it")
        }
    }

    override fun task02() {
        lines.filter {
            lineCorrupted(it) == 0
        }.map {
            computeAutocompleteScore(it)
        }.sorted().also {
            println("Task 02 solution: ${it[it.size / 2]}")
        }
    }


    private fun computeAutocompleteScore(line: String, stack: MutableList<Char> = mutableListOf()): Long {
        if (line != "") {
            when (val char = line.first()) {
                '(', '[', '{', '<' -> stack.add(0, char)
                ')', ']', '}', '>' -> stack.removeAt(0)
            }
            return computeAutocompleteScore(line.substring(1), stack)
        }
        return stack.map {
            when (it) {
                '(' -> 1
                '[' -> 2
                '{' -> 3
                else -> 4
            }
        }.fold(0L) { acc, i -> (acc * 5) + i }
    }

    private fun lineCorrupted(line: String, stack: MutableList<Char> = mutableListOf()): Int {
        if (line == "") return 0
        when (val char = line.first()) {
            '(' -> stack.add(0, ')')
            '[' -> stack.add(0, ']')
            '{' -> stack.add(0, '}')
            '<' -> stack.add(0, '>')
            ')', ']', '}', '>' -> {
                val expectedChar = stack.removeAt(0)
                if (char != expectedChar) {
                    when (char) {
                        ')' -> return 3
                        ']' -> return 57
                        '}' -> return 1197
                        '>' -> return 25137
                    }
                }
            }
        }
        return lineCorrupted(line.substring(1), stack)
    }
}