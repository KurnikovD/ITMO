import java.util.*

fun main() {
    val scan = Scanner(System.`in`)
    print("--   баллы   -- --  оценка  --\n" +
            "--   0 - 59    =      2     --\n" +
            "--  60 - 74    =      3     --\n" +
            "--  75 - 90    =      4     --\n " +
            "--  91 - 100   =      5     --\n" +
            "Введите количество балов: ")
    val point = scan.nextInt()
    print("Ваша оценка: ")

    if (point in 0..59)
        print(2)
    if (point in 60..74)
        print(3)
    if (point in 75..90)
        print(4)
    if (point in 91..100)
        print(5)
}