class GrammarRule {

    val grammarSymbol: Array<Char> = arrayOf('S', 'a', 'b', 'c', '#')
    private val terminal: Array<Char> = arrayOf('a', 'b', 'c')
    private val nonTerminal: Array<Char> = arrayOf('S')

    val rightRules = arrayOf("aSb", "b", "aScb")
    val leftRule: Array<Symboll> = Array(3) {Symboll('S', false)}

    val matrix = arrayOf(
            //      S  a  b  c  #
            arrayOf(0, 0, 1, 1, 0),     //S
            arrayOf(1, 3, 3, 0, 0),     //a
            arrayOf(0, 0, 2, 2, 2),     //b
            arrayOf(0, 0, 1, 0, 0),     //c
            arrayOf(3, 3, 3, 0, 0)      //#
        )

    fun isTerminal(input: Char): Boolean = terminal.contains(input)
    fun isNonTerminal(input: Char): Boolean = nonTerminal.contains(input)

}