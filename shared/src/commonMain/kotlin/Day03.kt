package day03

import readInput

fun main() {
    fun part1(input: String): Int {
        val multiplications = Regex("""mul\((\d+),(\d+)\)""").findAll(input).map {
            it.groups[1]!!.value.toInt() * it.groups[2]!!.value.toInt()
        }
        return multiplications.sum()
    }

    fun part2(input: String): Int {
        val commands = Regex("""mul\((\d+),(\d+)\)|do\(\)|don't\(\)""").findAll(input)
        var enabled = true
        var sum = 0
        for (command in commands) {
            when (command.value) {
                "do()" -> enabled = true
                "don't()" -> enabled = false
                else -> if (enabled) {
                    sum += command.groups[1]!!.value.toInt() * command.groups[2]!!.value.toInt()
                }
            }
        }
        return sum
    }

    check(part1("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))") == 161)
    check(part2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))") == 48)

    val input = readInput("day03")
    println(part1(input))
    println(part2(input))
}
