import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val pairs = input.map { it.split(" ", limit = 2) }
        val list1 = pairs.map { it[0].trim().toInt() }.sorted()
        val list2 = pairs.map { it[1].trim().toInt() }.sorted()
        return list1.zip(list2).sumOf { (a, b) -> (a - b).absoluteValue }
    }

    fun part2(input: List<String>): Int {
        val pairs = input.map { it.split(" ", limit = 2) }
        val list1 = pairs.map { it[0].trim().toInt() }.sorted()
        val list2 = pairs.map { it[1].trim().toInt() }.sorted()

        return list1.sumOf { n -> list2.count { it == n } * n }
    }

    val sample = "3   4\n4   3\n2   5\n1   3\n3   9\n3   3".lines()
    check(part1(sample) == 11)
    check(part2(sample) == 31)

    val input = readStrings("day01")
    part1(input).println()
    part2(input).println()
}
