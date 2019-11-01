import kotlin.math.max

class RR {

    fun planning(count: Int, gen: Generating) {

        val activeTime = IntArray(count)
        var waitTime = IntArray(count)
        var maxQueueLength = 0
        var durations = gen.duration
        var intervals = gen.interval

        // регистр состояний процессов (0 - не выполняется, 1 - ожидает, 2 - выполняется)
        val processStatus: IntArray = IntArray(count)

        // указатель на следующий поступающий в очередь процесс
        var indexOfNextProcess = 0

        // время до поступления в очередь следующего процесса int currentInterval = 0;
        val period: Int = 2
        var periodLeft: Int = period
        var finishedCount: Int = 0

        //очередь
        val turnList: MutableList<Int> = mutableListOf()
        var periodEnd: Boolean
        var durationEnd: Boolean
        processStatus[indexOfNextProcess] = 2
        //var index: Int = 0

        while(finishedCount < count) {
            if (indexOfNextProcess < intervals.size && intervals[indexOfNextProcess] == 0){
                turnList.add(indexOfNextProcess)
                maxQueueLength = max(maxQueueLength, turnList.count())
                processStatus[indexOfNextProcess] = 1
                indexOfNextProcess++
            }

            periodEnd = periodLeft == 0
            durationEnd = durations[indexOfNextProcess] == 0

            if (periodEnd || durationEnd){
                if (!durationEnd){
                    turnList.add(indexOfNextProcess)
                    processStatus[indexOfNextProcess] = 1
                }
                else {
                    processStatus[indexOfNextProcess] = 0
                    finishedCount++
                }
                turnList.removeAll(listOf(indexOfNextProcess))
                periodLeft = period
                if (turnList.size > 0){
                    indexOfNextProcess = getNextFromTurn(turnList)
                    processStatus[indexOfNextProcess] = 2
                }
            }

            if (indexOfNextProcess < intervals.size)
                --intervals[indexOfNextProcess]

            if (turnList.size > 0)
                --durations[indexOfNextProcess]
            periodLeft--

            // заполняем информационную часть новыми состояниями
            var index: Int = 0
            while (index < count){
                if (processStatus[index] == 1) ++waitTime[index]
                if (processStatus[index] == 2) ++activeTime[index]
                index++
            }
        }
    }

    fun getNextFromTurn(turnList: List<Int>): Int = turnList.first()
}