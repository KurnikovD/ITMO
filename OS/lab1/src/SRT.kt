import kotlin.math.max

class SRT : Plan() {
    lateinit var activeTime: IntArray
    lateinit var waitTime: IntArray
    var maxQueueLength = 0

    fun planning(count: Int, gen: Generating) {
        activeTime = IntArray(count)
        waitTime = IntArray(count)
        val durations = gen.duration
        val intervals = gen.interval
        // инициализация новых переменных
        // регистр состояний процессов (0 - не выполняется, 1 - ожидает, 2 - выполняется)
        val processStatus = IntArray(count)
        // указатель на следующий поступающий в очередь процесс
        var indexOfNextProcess = 0
        // указатель на текущий процесс
        var indexOfNowProcess = 0
        // время до завершения активного процесса
        var currentDuration = 0
        // время до поступления в очередь следующего процесса
        var curentInterval = 0
        //очередь
        val turnList: MutableList<Int> = mutableListOf()
        while (indexOfNextProcess < count || currentDuration > 0 || turnList.size > 0) {
            // если подоспел новый процесс
            if (curentInterval == 0 && ++indexOfNextProcess < count) {
                //ожидаем следующего
                curentInterval = intervals[indexOfNextProcess]
                // добавляем его в очередь
                turnList.add(indexOfNextProcess)
                maxQueueLength = max(maxQueueLength, turnList.count())
                // помечаем его как "ожидающий"
                processStatus[indexOfNextProcess] = 1
            }
            //проверяем процесс с меньшим временем
            if (processStatus[indexOfNowProcess] == 2 && turnList.size > 0) {
                val minIndex = minFromDuration(turnList, durations)
                if (currentDuration > durations[minIndex] && currentDuration != 0) {
                    //записываем оставшееся время
                    durations[indexOfNowProcess] = currentDuration
                    processStatus[indexOfNowProcess] = 1
                    turnList.add(indexOfNowProcess)
                    currentDuration = 0
                    turnList[turnList.indexOf(minIndex)] = turnList[0]
                    turnList[0] = minIndex
                }
            }
            // если закончился активный процесс
            if (currentDuration == 0) {
                // помечаем его как "завершенный"
                processStatus[indexOfNowProcess] = 0
                // если очередь не пуста
                if (turnList.size > 0) {
                    // берем следующий процесс из очереди
                    indexOfNowProcess = turnList.first()
                    // удаляем из очереди
                    turnList.removeAll(listOf(indexOfNowProcess))
                    // запоминаем его длительность
                    currentDuration = durations[indexOfNowProcess]
                    // помечаем его как "запущенный"
                    processStatus[indexOfNowProcess] = 2
                }
            }
            // уменьшаем время до завершения активного процесса
            currentDuration = max(0, --currentDuration)
            // уменьшаем время до поступления нового процесса в очередь
            curentInterval = max(0, --curentInterval)
            // заполняем информационную часть новыми состояниями
            //timeLinerDiagram.add()
            var index = 0
            while (index < count) {
                if (processStatus[index] == 1) ++waitTime[index]
                if (processStatus[index] == 2) ++activeTime[index]
                index++
            }
        }
    }

    fun minFromDuration(turnList: List<Int>, durations: IntArray): Int{
        val mass: MutableList<Int> = mutableListOf()
        var index: Int = 0
        var min: Int = 20
        for(turn in turnList)
            if (min > durations[turn]) {
                index = turn
                min = durations[turn]
            }
        return index
    }
    fun averafeProcessTime(): Double {
        return waitTime.average() + activeTime.average()
    }
    fun averafeWaitTime(): Double {
        return  waitTime.average()
    }
}
