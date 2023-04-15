data class Stick(val height: Int, val weight: Int)

fun main() {
    val sticks = listOf(
        Stick(3, 2), Stick(5, 4), Stick(7, 6), Stick(2, 1), Stick(8, 5),
        Stick(4, 3), Stick(6, 7), Stick(9, 9), Stick(1, 2), Stick(5, 4),
        Stick(7, 6), Stick(8, 8), Stick(3, 3), Stick(4, 5), Stick(6, 7),
        Stick(2, 1), Stick(1, 2), Stick(5, 4), Stick(8, 6), Stick(9, 8)
    )

    val sortedSticks = sticks.sortedBy { it.weight }

    val n = sortedSticks.size
    val dp = Array(51) { 0 }
    val sticksUsed = Array(51) { mutableListOf<Int>() }

    for (i in 0 until n) {
        for (j in 50 downTo sortedSticks[i].weight) {
            if (dp[j] < dp[j - sortedSticks[i].weight] + sortedSticks[i].height) {
                dp[j] = dp[j - sortedSticks[i].weight] + sortedSticks[i].height
                sticksUsed[j] = sticksUsed[j - sortedSticks[i].weight].toMutableList()
                sticksUsed[j].add(i)
            }
        }
    }

    val maxSum = dp[50]
    val usedSticks = sticksUsed[50]
    var usedWeight = 0
    for (i in usedSticks) {
        usedWeight += sortedSticks[i].weight
    }

    println("Max sum of heights: $maxSum")
    print("Sticks used: ")
    for (i in usedSticks.indices) {
        print("(${sortedSticks[usedSticks[i]].height}, ${sortedSticks[usedSticks[i]].weight})")
        if (i != usedSticks.size - 1) {
            print(", ")
        }
    }
    println()
    println("Sum of weights of used sticks: $usedWeight")
}
