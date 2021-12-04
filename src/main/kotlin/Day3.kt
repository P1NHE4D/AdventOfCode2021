import java.io.File

class Day3 : Day {
    override fun task01() {
        val data = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day3.txt").inputStream()
            .bufferedReader().use {
                it.readLines()
            }
        val mostCommonBits = data.mostCommonBits()
        val leastCommonBits = mostCommonBits.map {
            if (it == '0') {
                '1'
            } else {
                '0'
            }
        }.fold("") { acc, char -> acc + char }
        val gammaRate = Integer.parseInt(mostCommonBits, 2)
        val epsilonRate = Integer.parseInt(leastCommonBits, 2)
        println("Task 1 solution: ${gammaRate * epsilonRate}")
    }

    override fun task02() {
        val data = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day3.txt").inputStream()
            .bufferedReader().use {
                it.readLines()
            }
        val o2GenRating = data.findO2Rating()
        val co2ScrubberRating = data.findCO2Rating()
        print("Task 2 solution: ${o2GenRating * co2ScrubberRating}")
    }

}

fun List<String>.mostCommonBits(): String {
    val data = this.toTypedArray().map {
        it.toCharArray().map { char -> char.digitToInt() }
    }
    var mostCommonBits = ""
    for (i in data[0].indices) {
        var zeroes = 0
        var ones = 0
        for (j in data.indices) {
            if (data[j][i] == 0) {
                zeroes += 1
            } else {
                ones += 1
            }
        }
        mostCommonBits += if (ones >= zeroes) {
            "1"
        } else {
            "0"
        }
    }
    return mostCommonBits
}


fun List<String>.mostCommonBit(bitPosition: Int): Char {
    val data = this.toTypedArray().map {
        it.toCharArray().map { char -> char.digitToInt() }
    }
    var zeroes = 0
    var ones = 0
    for (j in data.indices) {
        if (data[j][bitPosition] == 0) {
            zeroes += 1
        } else {
            ones += 1
        }
    }
    return if (ones >= zeroes) {
        '1'
    } else {
        '0'
    }
}

fun List<String>.leastCommonBit(bitPosition: Int): Char {
    val data = this.toTypedArray().map {
        it.toCharArray().map { char -> char.digitToInt() }
    }
    var zeroes = 0
    var ones = 0
    for (j in data.indices) {
        if (data[j][bitPosition] == 0) {
            zeroes += 1
        } else {
            ones += 1
        }
    }
    return if (zeroes > ones) {
        '1'
    } else {
        '0'
    }
}

fun List<String>.findO2Rating(): Int {
    var temp = this
    var currentBit = 0
    while (temp.size > 1) {
        temp = temp.filter { it[currentBit] == temp.mostCommonBit(currentBit) }
        currentBit += 1
    }
    return Integer.parseInt(temp[0], 2)
}

fun List<String>.findCO2Rating(): Int {
    var temp = this
    var currentBit = 0
    while (temp.size > 1) {
        temp = temp.filter { it[currentBit] == temp.leastCommonBit(currentBit) }
        currentBit += 1
    }
    return Integer.parseInt(temp[0], 2)
}