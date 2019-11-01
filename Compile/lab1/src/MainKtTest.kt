import org.junit.jupiter.api.Test

internal class MainKtTest{
    val testingStringTrue: Array<String> = arrayOf(
         "abb",  "aabcbb", "aaabcbcbb", "aabbb", "b"
    )

    val testingStringFalse: Array<String> = arrayOf(
        "a", "c", "acb", "aab", "aacbb", "aa", "bb"
    )

    val testOtherLetterInGrammar: Array<String> = arrayOf(
        "aabcbd", "e", "1", "@", "#"
    )

    val testNonTerminalInGrammar: Array<String> = arrayOf(
        "aSb", "S", "aScb", "aaSbb", "aaSbb", "SS"
    )

    @Test
    fun testTrue(){
        for (test in testingStringTrue)
            assert(GrammarCheck(test, GrammarRule()).isGrammarValid() == "TRUE")

    }

    @Test
    fun testFalse(){
        for (test in testingStringFalse)
            assert(GrammarCheck(test, GrammarRule()).isGrammarValid() == "ERROR: String is not equals to grammar!")
    }

    @Test
    fun testEmptyGrammar(){
        assert(GrammarCheck("", GrammarRule()).isGrammarValid() == "ERROR: Grammars is empty!")
    }
    @Test
    fun testOtherLetterInGrammar(){
        for (test in testOtherLetterInGrammar) {
            var st = GrammarCheck(test, GrammarRule()).isGrammarValid()
            assert( st == "ERROR: String have terminal not from rule!")
        }
    }
    @Test
    fun testGrammarForNonTerminals(){
        for (test in testNonTerminalInGrammar)
            assert(GrammarCheck(test, GrammarRule()).isGrammarValid() == "ERROR: String have nonTerminal!")
    }


}
