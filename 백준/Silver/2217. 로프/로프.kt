// boj 2217
fun main() {
    val n = readln().toInt()
    val mutableList: MutableList<Int> = mutableListOf()
    for (i in 0 until n) {
        mutableList.add(readln().toInt())
    }
    
    mutableList.sortDescending()
    var min = 0
    for (i in 0 until n) {
        val temp = mutableList[i] * (i + 1)
        if (temp > min) {
            min = temp
        }
    }
    println(min)
}
