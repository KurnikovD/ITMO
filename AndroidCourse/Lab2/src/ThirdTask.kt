import java.util.*

fun main() {
    val scan = Scanner(System.`in`)
    val s1 = scan.next()
    val s2 = scan.next()
    val s3 = scan.next()
    val s4 = scan.next()
    val s5 = scan.next()
    val resultArray = mutableListOf<String>()
    var result: String = ""
    val list = mutableListOf<String>(s1, s2, s3, s4, s5)
    var count: Int = list.count{ it == list.first() }
    while (list.isNotEmpty()) {
        count = list.count { it == list.first() }
        if (count > 1)
            resultArray.addAll(Array<String>(count) {list.first()})

        list.removeIf { it == list.first() }
    }

    when (resultArray.size){
        0 -> result = "Все элементы разные"
        5 -> resultArray.forEach { element -> result += "$element, " }
        in 1..4 -> resultArray.forEach { element -> result += "$element " }
    }

    println(result)
}