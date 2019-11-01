import java.util.*

fun main() {
    val scan = Scanner(System.`in`)
    print("--   баллы   -- --  оценка  --\n" +
            "--   0 - 59    =      2     --\n" +
            "--  60 - 74    =      3     --\n" +
            "--  75 - 90    =      4     --\n " +
            "--  91 - 100   =      5     --\n" +
            "Введите количество балов: ")
    when (scan.nextInt()){
        in 0..59 -> println("Ваша лценка: 2")
        in 60..74 -> println("Ваша лценка: 3")
        in 75..90 -> println("Ваша лценка: 4")
        in 91..100 -> println("Ваша лценка: 5")
    }
}