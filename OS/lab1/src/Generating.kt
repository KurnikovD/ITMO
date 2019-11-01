import kotlin.random.Random

class Generating {
    private val maxDuration: Int = 12
    private val minDuration: Int = 3
    private val maxInterval: Int = 7
    private val minInterval: Int = 1

    lateinit var duration: IntArray
    lateinit var interval: IntArray

    fun generate(count: Int) {
        duration = List(count) { Random.nextInt(minDuration, maxDuration) }.toIntArray()
        interval = List(count) { Random.nextInt(minInterval, maxInterval) }.toIntArray()
        interval[0] = 0
    }
}