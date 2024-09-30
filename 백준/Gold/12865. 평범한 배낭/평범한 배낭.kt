import java.lang.Integer.max

fun main() {
    val input = readln().split(" ").map { it.toInt() }
    val n = input[0]
    val k = input[1]

    val weight: MutableList<Int> = mutableListOf()
    val value: MutableList<Int> = mutableListOf()

    for (i in 0 until n) {
        val input = readln().split(" ").map { it.toInt() }
        weight.add(input[0])
        value.add(input[1])
    }

    val arr = Array(101) { IntArray(100001) }
    
    for (i in 1 until n + 1) {
        for (j in 1 until k + 1) {
            if (weight[i - 1] <= j) {
                arr[i][j] = max(arr[i - 1][j], arr[i - 1][j - weight[i - 1]] + value[i - 1])
            } else {
                arr[i][j] = arr[i - 1][j]
            }
        }
    }
    print(arr[n][k])
}
