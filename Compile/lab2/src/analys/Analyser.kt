package analys

import java.io.File

class Analyser(val fileName: String) {

    private var t = IntArray(2) {0} // token

    var lines = mutableListOf<String>()
    var log: Log = Log()
    var Lexeme: String = ""
    var LexRow: Int = 0
    var LexCol: Int = 0
    var LexKind: Int = 0
    var vars_table = mutableListOf<String>()

    fun Analyser() {
        var i = 0
        for (line in File(fileName).readLines())
            lines.add(line)
//            var j = 0
//            for (char in line) {
//                token[i].add()
//            }
    }

    fun program(){
//        <Программа> ::= <Объявление переменных> <Описание вычислений>
//        <program> -> <vars_definition> <computation_definition>
        while (lines.size >= LexRow) {
            do {
               do{
                    if (Lexeme.length > 0 && Lexeme.first() == ' ')
                        Lexeme.removeRange(0, 1)
                    getNextSymbol()
                }while (Lexeme.last() != ' ' && Lexeme.last() != '\n')
                Lexeme = Lexeme.removeRange(Lexeme.length - 1, Lexeme.length)
                when (Lexeme) {
                    "Var" -> vars_definition()
                    "Begin" -> computation_definition()
                    //"end" -> computation_definition()
                    else -> log.addError("Unknown lexeme", LexRow, LexCol)
                }
            }while (Lexeme.isEmpty()|| Lexeme.last() != '\n')
            LexRow++
            t[0]++
        }
        log.writeLog()
    }

    fun vars_definition() {
//        <Объявление переменных> ::= Var <Список переменных> :Boolean; Var <Список переменных> :Boolean;| <Объявление переменных>
//        <Объявление переменных> ::= Var <Список переменных> :Decimal; Var <Список переменных> :Decimal; <Объявление переменных>
//
//        <vars_definition> -> Var <var_list> :Boolean; Var <var_list> :Boolean;| <vars_definition>
//        <vars_definition> -> Var <var_list> :Decimal; Var <var_list> :Decimal;| <vars_definition>

        log.addLexeme(Lexeme, t)
        t[1]++
        Lexeme = ""
        var_list()

        do{
            if (Lexeme.length > 0){
                if (Lexeme.last() == '\n')
                    log.addError("Excepted ';'", LexRow, LexCol)
                if (Lexeme.first() == ' ')
                    log.addError("Excepted type", LexRow, LexCol)
            }
            getNextSymbol()
        }while (Lexeme.last() != ';')
        Lexeme = Lexeme.removeRange(Lexeme.length - 1, Lexeme.length)
        if (TYPES.contains(Lexeme)){
            log.addLexeme(Lexeme, t)
            t[1]++
        }

        log.addLexeme(";", t)
        t[1]++
        Lexeme = ""

    }

    fun var_list(){
//        <Список переменных> ::= <Идент> :| <Идент> , <Список переменных> :
//        <var_list> -> <ident> | <ident>, <var_list>
        do {
            if (Lexeme.length > 0 && Lexeme.first() == ' ')
                Lexeme = Lexeme.removeRange(0, 1)
            if (Lexeme.length > 2 && Lexeme.contains(' ') && Lexeme.last() != ' ') {
                log.addError("Excepted ',' or ':'", LexRow, LexCol)
                Lexeme = ""
                break
            }
            else {

                if (Lexeme.length > 0 && Lexeme.last() == ',') {
                    Lexeme = Lexeme.removeRange(Lexeme.length - 1, 1)
                    if (Lexeme.isNotEmpty()) {
                        ident(Lexeme, true)
                        t[1]++
                        log.addLexeme(",", t)
                        t[1]++
                        Lexeme = ""
                    } else
                        log.addError("Excepted identify", LexRow, LexCol)
                }
                if (Lexeme.isNotEmpty() && !CHARS.contains(Lexeme.last())) {
                    log.addError("Letter not contains inciter", LexRow, LexCol)
                    Lexeme = ""
                }
                getNextSymbol()
            }
        }while (Lexeme.last() != ':')
        Lexeme = Lexeme.removeRange(Lexeme.length - 1, 1)

        if (Lexeme.isNotEmpty() && Lexeme.last() == ' ')
            Lexeme = Lexeme.removeRange(Lexeme.length - 1, Lexeme.length)

        ident(Lexeme, true)
        t[1]++
        log.addLexeme(":", t)
        t[1]++
        Lexeme = ""
    }

    fun computation_definition() {
//        <Описание вычислений> ::= Begin <Список присваиваний> End
//        <computation_definition> -> Begin <operator_list> End

            log.addLexeme(Lexeme, t)
            t[1]++
            Lexeme = ""
            operator_list()
    }

    fun operator_list() {
//        <Список операторов> ::= <Оператор> | <Оператор> <Список операторов>
//        <operator_list> -> <operator> | <operator> <operator_list>

        while (Lexeme != "End"){
            operator()
        }
        log.addLexeme(Lexeme, t)
        t[1]++
        Lexeme = ""
    }

    fun operator() {
//        <Оператор> ::= <Присваивание> | <Сложный оператор>
//        <operator> -> <assigment> | <complex_operator>

        while (Lexeme.last() != ':' || Lexeme.last() != ' '){
            if (Lexeme.first() == ' ')
                Lexeme = Lexeme.removeRange(0, 1)
            getNextSymbol()
        }

        when (Lexeme.removeRange(Lexeme.length - 1, Lexeme.length)){
            "IF" -> complex_operator()
            else -> assigment()
        }

    }

    fun assigment() {
//        <Присваивание> ::= <Идент> := <Выражение>
//        <assigment> -> <ident> := <expr>

        while (Lexeme.last() != ':') {
            if (Lexeme.length > 2 && Lexeme.contains(' ') && Lexeme.last() != ' ') {
                log.addError("Excepted ':'", LexRow, LexCol)
                Lexeme = ""
                break
            }
            else {
                if (Lexeme.last() == ':'){
                    ident(Lexeme.removeRange(Lexeme.length - 1, Lexeme.length))
                    t[1]++
                    getNextSymbol()
                    if (Lexeme.last() == '=') {
                        log.addLexeme(":=", t)
                        t[1]++
                        Lexeme = ""
                        while (Lexeme.last() != '\n')
                            getNextSymbol()
                        Lexeme = Lexeme.removeRange(Lexeme.length - 1, Lexeme.length)
                        expr(Lexeme)
                        t[0]++
                        LexCol = 0
                        LexRow++
                    }
                    else {
                        log.addError("Wrong operator", LexRow, LexCol)
                        break
                    }
                }
                getNextSymbol()
            }
        }


    }

    fun expr(st: String) {
//        <Выражение> ::= <Ун.оп.> <Подвыражение> | <Подвыражение>
//        <expr> -> <un_op> <sub_expr> | <sub_expr>
        var lex = st
        if (UN_OP.contains(lex.first().toString())) {
            un_op()
            lex = lex.removeRange(0, 1)
            sub_expr(lex)
        }
        else sub_expr(lex)
    }

    fun sub_expr(st: String) {
//        <Подвыражение> ::= ( <Выражение> ) | <Операнд> | <Подвыражение> <Бин.оп.> <Подвыражение>
//        <sub_expr> -> (<expr>) | <operand> | <sub_expr> <din_op> <sub_expr>
        var lex = st
        if (BRACKETS.contains(lex.first().toString())){
            log.addLexeme(lex.first().toString(), t)
            t[1]++
            lex = lex.removeRange(0, 1)
            expr(lex)
            log.addLexeme(lex.first().toString(), t)
            t[1]++
            lex  = lex.removeRange(0, 1)
        }else if (vars_table.contains(lex) || NUMBERS.contains(lex)){
            operand(lex)
        }else {
            var a: String = ""
            while (BIN_OP.contains(a.last().toString()) || a.last() != '\n' || a.last() != ')') {
                a += lex.first()
                lex = lex.removeRange(0, 1)
            }
            if (lex.last() == '\n'){
                log.addError("Wrong expression", LexRow, LexCol)
                return
            }
            val op = a.last().toString()
            a = a.removeRange(Lexeme.length - 1, 1)
            sub_expr(a)
            bin_op(op)
            sub_expr(lex)
        }

    }

    fun un_op() {
//        <Ун.оп.> ::= "!"
//        <un_op> -> "!"

        log.addLexeme(Lexeme, t)
        t[1]++
        Lexeme = ""
    }

    fun bin_op(op: String) {
//        <Бин.оп.> ::= "&" | "|" | "^" | "-" | "+" | "*" | "/" | ">" | "<" | "="
//        <bin_op> -> "&" | "|" | "^" | "-" | "+" | "*" | "/" | ">" | "<" | "="
        log.addLexeme(op, t)
        t[1]++
    }

    fun operand(st: String) {
//        <Операнд> ::= <Идент> | <Const>
//        <operand> -> <ident> | <const>

        var lex = st
        while (lex.first() == ' ' || lex.last() == ' ') {
            if (lex.first() == ' ')
                lex = lex.removeRange(0, 1)
            if (lex.last() == ' ')
                lex = lex.removeRange(lex.length - 1, 1)
        }
        if (lex.contains(' ')){
            log.addError("WrongArgument", LexRow, LexCol)
            return
        }
        if (vars_table.contains(lex))
            ident(lex)
        else const(lex)
    }

    fun complex_operator(){
//        <Сложный оператор> ::= IF "("< Выражение> ")" Оператор | IF "(" <Выражение> ")" <Оператор> ELSE <Оператор>
//        <complex_operator> -> IF (<expr>) <operator> | IF (<expr>) <operator> ELSE <operator>

    }

    fun ident(id: String, new: Boolean = false) {
//    <Идент> ::= <Буква> <Идент> | <Буква>
//    <ident> -> <letter> <ident> | <letter>

        if (new) {
            if (vars_table.contains(id))
                log.addError("identify already exist!", LexRow, LexCol)
            else {
                vars_table.add(id)
                log.addIdentify(vars_table.indexOf(id), t)
            }
        }
        else{
            if (vars_table.contains(id))
                log.addIdentify(vars_table.indexOf(id), t)
            else
                log.addError( "Identify don't describe!", LexRow, LexCol)
        }

    }

    fun const(lex: String) {
//        <Const> ::= <LConst> | <DConst>
//        <const> -> <b_const> | <d_const>
        if (lex.length > 1)
            d_const(lex)
        else
            l_const(lex)
    }

    fun d_const(st: String) {
//        <DConst> ::= <Цифра> | <Цифра><DConst>
//        <d_const> -> <DIGIT> | <DIGIT><d_const>

        for (s in st)
            if(!NUMBERS.contains(s)) {
                log.addError("Not DConst", LexRow,LexCol)
                return
            }
        log.addDConst(st.toInt(), t)
        t[1]++
    }

    fun l_const(st: String) {
//        <BConst> ::= 0 | 1
//        <b_const> -> '0' | '1'
        if (!LCONST.contains(st)){
            log.addError("Not LConst", LexRow,LexCol)
            return
        }
        log.addLConst(st, t)
        t[1]++

    }


    fun getLexToSymbol(seporateChar: Char): String{
        var char: Char
        do {
            LexCol++
            char = lines[LexRow][LexCol]
            Lexeme += char
        }while (char != seporateChar)
        return Lexeme.removeRange(Lexeme.length - 1, Lexeme.length)
    }
    fun getNextSymbol(){
        Lexeme += lines[LexRow][LexCol]
        LexCol++
    }
}
