import java.io.File
import Node as Node

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

    private val startNode = Node(
        value = "start",
        parent = null,
        children = caveConnections["start"]!!,
        smallNode = true,
    )

    override fun task01() {
        findAllPathsTask1(startNode).also {
            println("Task 01 solution: $it")
        }
    }

    override fun task02() {
        findAllPathsTask2(startNode).also {
            println("Task 02 solution: $it")
        }
    }

    private fun findAllPathsTask1(node: Node, visitedNodes: List<String> = listOf()): Int {
        if (node.value == "end") return 1
        if (node.value == "start" && visitedNodes.isNotEmpty()) return 0
        if (node.smallNode && visitedNodes.contains(node.value)) return 0
        return node.children.fold(0) { acc, child ->
            val childNode = Node(
                value = child,
                parent = node,
                children = caveConnections[child]!!,
                smallNode = child.all { it.isLowerCase() },
            )
            acc + findAllPathsTask1(childNode, visitedNodes + node.value)
        }
    }

    private fun findAllPathsTask2(node: Node, visitedNodes: List<String> = listOf()): Int {
        if (node.value == "end") return 1
        if (node.value == "start" && visitedNodes.isNotEmpty()) return 0
        if (node.smallNode &&
            visitedNodes.contains(node.value) &&
            !visitedNodes.filter { it.all { c -> c.isLowerCase() } }.groupingBy { it }.eachCount().all { it.value <= 1 }
        ) return 0
        return node.children.fold(0) { acc, child ->
            val childNode = Node(
                value = child,
                parent = node,
                children = caveConnections[child]!!,
                smallNode = child.all { it.isLowerCase() },
            )
            acc + findAllPathsTask2(childNode, visitedNodes + node.value)
        }
    }
}

data class Node(
    val value: String,
    val parent: Node?,
    val children: Set<String>,
    val smallNode: Boolean = false,
)

