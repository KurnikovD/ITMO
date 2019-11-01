import java.util.*

class GrammarCheck(private var input: String, private val rule: GrammarRule) {

    private var stack: Stack<Symboll> = Stack()

    fun isGrammarValid(): String{

        if (input.isEmpty()) return "ERROR: Grammars is empty!"
        if (!checkStringForNonTerminal()) return "ERROR: String have nonTerminal!"
        if (!checkString()) return "ERROR: String have terminal not from rule!"

        input += '#'
        stack.push(Symboll('#', true))

        while(true){
            if (input[0] == '#' && stack.size == 2 && stack.peek().Value == 'S') return "TRUE"

            val i = rule.grammarSymbol.indexOf(stack.peek().Value)
            val j = rule.grammarSymbol.indexOf(input[0])
            when (rule.matrix[i][j]) {
                0 -> return "ERROR: String is not equals to grammar!"
                1 -> next()
                2 -> if (!doMore()) return "ERROR: String is not equals to grammar!"
                3 -> next()
                else -> return "ERROR: Strange error!"
            }
        }

    }

    private fun doMore(): Boolean {
        var removeString: String = ""

        while(true) {
            removeString = stack.pop().Value + removeString
            val i: Int = rule.grammarSymbol.indexOf(stack.peek().Value)
            val j: Int = rule.grammarSymbol.indexOf(removeString[0])
            if (rule.matrix[i][j] == 0)
                return false
            if (rule.matrix[i][j] == 3) break
        }

        if (rule.rightRules.contains(removeString))
            stack.push(rule.leftRule[rule.rightRules.indexOf(removeString)])
        else
            return false
        return true
    }

    private fun next() {
        stack.push(Symboll(input[0], rule.isTerminal(input[0])))
        input = input.removeRange(0, 1)
    }

    private fun checkString(): Boolean {
        for (char in input)
            if (!rule.isTerminal(char))
                return  false
        return true
    }

    private fun checkStringForNonTerminal(): Boolean {
        for (char in input)
            if (rule.isNonTerminal(char))
                return  false
        return true
    }
}