import java.io.File

class Day12 : Day {
    /**
     * map start : [all caves that are connected to start]
     *
     */
    private val caveConnections =
        File("/Users/agerlach/Projects/advent_of_code_2021/src/main/resources/input/day12.txt")
            .inputStream()
            .bufferedReader()
            .use { it.readLines() }
            .map { it.split("-") }
            .fold(mutableMapOf<String, Set<String>>()) { map, list ->
                map[list[0]] = map.getOrElse(list[0]) { mutableSetOf() }.plus(list[1])
                map[list[1]] = map.getOrElse(list[1]) { mutableSetOf() }.plus(list[0])
                map
            }

    override fun task01() {
        findAllPathsTask1("start").also {
            println("Task 01 solution: $it")
        }
    }

    override fun task02() {
        findAllPathsTask2("start").also {
            println("Task 02 solution: $it")
        }
    }

    private fun findAllPathsTask1(node: String, visitedNodes: List<String> = listOf()): Int {
        if (node == "end") return 1
        if (node == "start" && visitedNodes.isNotEmpty()) return 0
        if (node.all { it.isLowerCase() } && visitedNodes.contains(node)) return 0
        return caveConnections[node]!!.fold(0) { acc, child ->
            acc + findAllPathsTask1(child, visitedNodes + node)
        }
    }

    private fun findAllPathsTask2(node: String, visitedNodes: List<String> = listOf()): Int {
        if (node == "end") return 1
        if (node == "start" && visitedNodes.isNotEmpty()) return 0
        if (node.all { it.isLowerCase() } &&
            visitedNodes.contains(node) &&
            !visitedNodes.filter { it.all { c -> c.isLowerCase() } }.groupingBy { it }.eachCount().all { it.value <= 1 }
        ) return 0
        return caveConnections[node]!!.fold(0) { acc, child ->
            acc + findAllPathsTask2(child, visitedNodes + node)
        }
    }
}

