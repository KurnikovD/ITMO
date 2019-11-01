fun main() {
    val mass = IntArray(6) { 0 }
    try {
        var num = mass[6]
    } catch (e: Exception) {
        println(e.message)
    }
}
