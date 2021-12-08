import java.io.File
import kotlin.math.absoluteValue

class Day7 : Day {
    private val data = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day7.txt")
        .inputStream()
        .bufferedReader()
        .use { it.readLine() }
        .split(",")
        .map { it.toInt() }

    override fun task01() {
        val approximatedLevel = data
            .groupingBy { it }
            .eachCount()
            .maxByOrNull { it.value }?.key ?: 0
        val approximation = data.sumOf { (it - approximatedLevel).absoluteValue }
        optimiseFuelConsumption(approximatedLevel, approximation) { pos, level -> (pos - level).absoluteValue }.also {
            println("Task 1 solution: $it")
        }
    }

    override fun task02() {
        val approximatedLevel = data
            .groupingBy { it }
            .eachCount()
            .maxByOrNull { it.value }?.key ?: 0
        val approximation = data.sumOf { IntRange(1, (it - approximatedLevel - 1).absoluteValue).sum() }
        optimiseFuelConsumption(approximatedLevel, approximation) { pos, level -> IntRange(1, (pos - level - 1).absoluteValue).sum()  }.also {
            println("Task 2 solution: $it")
        }
    }

    private fun optimiseFuelConsumption(level: Int, approximation: Int, costFunction: (Int, Int) -> Int) : Int {
        var optimisedConsumption = data.sumOf { costFunction(it, level - 1) }
        if (optimisedConsumption < approximation) return optimiseFuelConsumption(level - 1, optimisedConsumption, costFunction)
        optimisedConsumption = data.sumOf { costFunction(it, level + 1) }
        if (optimisedConsumption < approximation) return optimiseFuelConsumption(level + 1, optimisedConsumption, costFunction)
        return approximation
    }
}


