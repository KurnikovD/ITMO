fun main(args: Array<String>){
    if (args.size < 1) {
        print("ERRR")
        System.exit(1)
    }
    val step = 10
    var min = 30
    val max = 50
    while (min <= max) {
        val gen = Generating()
        gen.generate(min)
        println("----------------------------------------------------------${min}----------------------------------------------------------")
        print("Duration: ")
        for (i in gen.duration)
            print("$i ")
        print("\nInterval: ")
        for (i in gen.interval)
            print("$i ")

        val planer1 = FCFS()
        planer1.planning(min, gen)
        val planer2 = SRT()
        planer2.planning(min, gen)

        println("\n---------FCFS---------")
        println("Average time: ${planer1.averafeProcessTime()}\nWaiting: ${planer1.averafeWaitTime()}\nMax queue: ${planer1.maxQueueLength}")
        println("---------SRT---------")
        println("Average time: ${planer2.averafeProcessTime()}\nWaiting: ${planer2.averafeWaitTime()}\nMax queue: ${planer2.maxQueueLength}\n")

        min += step
    }
}

