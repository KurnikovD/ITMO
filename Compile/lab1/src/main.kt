fun main(args: Array<String>) {

    if (args.isEmpty()){
        print("ERROR: ")
        System.exit(1)
    }

    println("Input string: ${args[0]}")

    println(GrammarCheck(args[0], GrammarRule()).isGrammarValid())

    println("Press any key to exit...")
    readLine()
}