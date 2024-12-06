package day05

import readInput

private fun List<Int>.isCorrect(ordering: Map<Int, List<Int>>): Boolean {
    for (i in indices) {
        if (this[i] in ordering) {
            val after = ordering[this[i]]!!
            for (j in 0 until i) {
                if (this[j] in after) return false
            }
        }
    }
    return true
}

private fun List<Int>.fixOrder(ordering: Map<Int, List<Int>>): List<Int> {
    return sortedWith { a, b ->
        if (a in ordering && b in ordering[a]!!) -1
        else if (b in ordering && a in ordering[b]!!) 1
        else 0
    }
}

private fun parse(input: String): Pair<Map<Int, List<Int>>, List<List<Int>>> {
    val (orderingRules, pages) = input.split("\n\n")
    val ordering = orderingRules.lines().map {
        val rule = it.split('|')
        rule[0].toInt() to rule[1].toInt()
    }.groupBy({ it.first }) { it.second }
    val updates = pages.lines().map { it.split(',').map(String::toInt) }
    return Pair(ordering, updates)
}

fun part1(input: String): Int {
    val (ordering, updates) = parse(input)
    return updates.filter { it.isCorrect(ordering) }.sumOf { it[it.size / 2] }
}

fun part2(input: String): Int {
    val (ordering, updates) = parse(input)
    return updates.filterNot { it.isCorrect(ordering) }.map { it.fixOrder(ordering) }.sumOf { it[it.size / 2] }
}

val day5sample = "47|53\n" +
        "97|13\n" +
        "97|61\n" +
        "97|47\n" +
        "75|29\n" +
        "61|13\n" +
        "75|53\n" +
        "29|13\n" +
        "97|29\n" +
        "53|29\n" +
        "61|53\n" +
        "97|53\n" +
        "61|29\n" +
        "47|13\n" +
        "75|47\n" +
        "97|75\n" +
        "47|61\n" +
        "75|61\n" +
        "47|29\n" +
        "75|13\n" +
        "53|13\n" +
        "\n" +
        "75,47,61,53,29\n" +
        "97,61,53,29,13\n" +
        "75,29,13\n" +
        "75,97,47,61,53\n" +
        "61,13,29\n" +
        "97,13,75,29,47"


fun main() {
    val input = readInput("day05")
    println(part1(day5sample))
    println(part1(input))
    println(part2(day5sample))
    println(part2(input))
}
