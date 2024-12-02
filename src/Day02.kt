import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    class Report(val levels: List<Int>) {
        val isSafe: Boolean
            get() {
                val diffs = levels.zipWithNext { a, b -> b - a }
                return diffs.all { it.absoluteValue in 1..3 } && diffs.map { it.sign }.distinct().size == 1
            }

        val isTolerated: Boolean
        get() {
            for (i in levels.indices) {
                val before = if (i > 0) levels.subList(0, i) else emptyList()
                val after = if (i < levels.size - 1) levels.subList(i + 1, levels.size) else emptyList()
                if (Report(before + after).isSafe)
                    return true
            }
            return false
        }
    }

    fun part1(input: List<String>): Int {
        val reports = input.map { Report(it.split(" ").map { it.trim().toInt() }) }
        return reports.count { it.isSafe }
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { Report(it.split(" ").map { it.trim().toInt() }) }
        return reports.count { it.isSafe || it.isTolerated }
    }

    val sample = "7 6 4 2 1\n1 2 7 8 9\n9 7 6 2 1\n1 3 2 4 5\n8 6 4 4 1\n1 3 6 7 9".lines()
    check(part1(sample) == 2)
    check(part2(sample) == 4)

    val input = readInput("day02")
    part1(input).println()
    part2(input).println()
}
