import java.io.File

class Day5 : Day {
    private val data = File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day5.txt")
        .inputStream()
        .bufferedReader()
        .use {
            it.readLines()
        }
        .map {
            it.replace(" -> ", ",").split(",").map { str -> str.toInt() }
        }

    override fun task01() {
        HydrothermalField(1000, 1000).apply {
            data.filter { (it[0] == it[2]) || (it[1] == it[3]) }.forEach {
                drawLine(it[0], it[1], it[2], it[3])
            }
        }.also {
            println("Task 01 solution: ${it.overlappingPoints}")
        }
    }

    override fun task02() {
        HydrothermalField(1000, 1000).apply {
            data.forEach {
                drawLine(it[0], it[1], it[2], it[3])
            }
        }.also {
            println("Task 02 solution: ${it.overlappingPoints}")
        }
    }
}

class HydrothermalField(xSize: Int, ySize: Int) {
    private val field = Array(xSize) {
        IntArray(ySize) { 0 }
    }
    var overlappingPoints = 0

    fun drawLine(xStart: Int, yStart: Int, xEnd: Int, yEnd: Int) {
        var y = yStart
        var x = xStart
        var finished = false
        while (!finished) {
            field[x][y] += 1
            if (field[x][y] == 2) {
                overlappingPoints += 1
            }
            if (xStart != xEnd) {
                if (xStart > xEnd) {
                    x -= 1
                    finished = (x < xEnd)
                } else {
                    x += 1
                    finished = (x > xEnd)
                }
            }
            if (yStart != yEnd) {
                if (yStart > yEnd) {
                    y -= 1
                    finished = (y < yEnd)
                } else {
                    y += 1
                    finished = (y > yEnd)
                }
            }
        }
    }
}