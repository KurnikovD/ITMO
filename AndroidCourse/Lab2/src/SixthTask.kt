import java.util.*


fun main() = println(IntArray(15) { i -> Scanner(System.`in`).nextInt()}.also { it.sort() }.joinToString())