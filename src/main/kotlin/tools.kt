fun <T> List<List<T>>.transpose() : List<List<T>>{
    val transposed: MutableList<MutableList<T>> = mutableListOf()
    for (row in this.indices) {
        val transposedRow = mutableListOf<T>()
        for (col in this[row].indices) {
            transposedRow.add(col, this[col][row])
        }
        transposed.add(row, transposedRow)
    }
    return transposed.toList()
}