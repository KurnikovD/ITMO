package analys

import java.io.File

class Analitick(fileName: String) {

    var lines = mutableListOf<String>()
    var lexeme: String = ""
    var fileName = fileName
    var token = IntArray(2) {0}
    var log = Log()
    var var_table = mutableListOf<String>()
    fun programm() {
//        <Программа> ::= <Объявление переменных> <Описание вычислений>.
//        <program> -> <vars_definition> <computation_definition>

        for (line in File(fileName).readLines()){
            var st = line.split(' ')
            when (st[0]){
                "Var" -> vars_definition(st)
            }
        }

    }

    fun vars_definition(vars: List<String>) {
//        <Объявление переменных> ::= Var <Список переменных> :Boolean; Var <Список переменных> :Boolean;| <Объявление переменных>
//        <Объявление переменных> ::= Var <Список переменных> :Decimal; Var <Список переменных> :Decimal; <Объявление переменных>
//
//        <vars_definition> -> Var <var_list> :Boolean; Var <var_list> :Boolean;| <vars_definition>
//        <vars_definition> -> Var <var_list> :Decimal; Var <var_list> :Decimal;| <vars_definition>

        log.addLexeme("Var", token)
        token[1]++

        var_list(vars.rem)
    }

    fun computation_definition() {
//        <Описание вычислений> ::= Begin <Список присваиваний> End
//        <computation_definition> -> Begin <operator_list> End

    }

    fun var_list(var_: List<String>) {
//        <Список переменных> ::= <Идент> :| <Идент> , <Список переменных> :
//        <var_list> -> <ident> | <ident>, <var_list>

    }

    fun operator_list() {
//        <Список операторов> ::= <Оператор> | <Оператор> <Список операторов>
//        <operator_list> -> <operator> | <operator> <operator_list>

    }

    fun operator() {
//        <Оператор> ::= <Присваивание> | <Сложный оператор>
//        <operator> -> <assigment> | <complex_operator>

    }

    fun assigment() {
//        <Присваивание> ::= <Идент> := <Выражение>
//        <assigment> -> <ident> := <expr>

    }

    fun complex_operator() {
//        <Сложный оператор> ::= IF "("< Выражение> ")" Оператор | IF "(" <Выражение> ")" <Оператор> ELSE <Оператор>
//        <complex_operator> -> IF (<expr>) <operator> | IF (<expr>) <operator> ELSE <operator>

    }

    fun ident() {
//    <Идент> ::= <Буква> <Идент> | <Буква>
//    <ident> -> <letter> <ident> | <letter>

    }

    fun expr() {
//        <Выражение> ::= <Ун.оп.> <Подвыражение> | <Подвыражение>
//        <expr> -> <un_op> <sub_expr> | <sub_expr>

    }

    fun un_op(){
//        <Ун.оп.> ::= "!"
//        <un_op> -> "!"

    }

    fun sub_expr() {
//        <Подвыражение> ::= ( <Выражение> ) | <Операнд> | <Подвыражение> <Бин.оп.> <Подвыражение>
//        <sub_expr> -> (<expr>) | <operand> | <sub_expr> <bin_op> <sub_expr>

    }

    fun operand() {
//        <Операнд> ::= <Идент> | <Const>
//        <operand> -> <ident> | <const>

    }

    fun bin_op() {
//        <Бин.оп.> ::= "&" | "|" | "^" | "-" | "+" | "*" | "/" | ">" | "<" | "="
//        <bin_op> -> "&" | "|" | "^" | "-" | "+" | "*" | "/" | ">" | "<" | "="

    }

    fun const() {
//        <Const> ::= <BConst> | <DConst>
//        <const> -> <b_const> | <d_const>

    }

    fun b_const() {
//        <BConst> ::= 0 | 1
//        <b_const> -> '0' | '1'

    }

    fun d_const() {
//        <DConst> ::= <Цифра> | <Цифра><DConst>
//        <d_const> -> <DIGIT> | <DIGIT><d_const>

    }
}