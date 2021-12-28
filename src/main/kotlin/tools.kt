fun <T> List<List<T>>.transpose2() : List<List<T>>{
    val transposed: MutableList<MutableList<T>> = mutableListOf()
    var col = 0
    while (col <= this[0].lastIndex) {
        val transposedRow = mutableListOf<T>()
        for (row in this.indices) {
            transposedRow.add(this[row][col])
        }
        col++
        transposed.add(transposedRow)
    }

    return transposed.toList()
}

inline fun <reified T> List<List<T>>.transpose() : List<List<T>> {
    val transposed = Array(this[0].size) {Array(this.size) {
        this[0][0]
    } }
    for (row in 0..transposed.lastIndex) {
        for (col in 0..transposed[0].lastIndex) {
            transposed[row][col] = this[col][row]
        }
    }
    return transposed.map { it.toList() }.toList()
}