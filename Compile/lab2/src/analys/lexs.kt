package analys

enum class Lex {
    ID, CONST, DCONST, BCONST, BIN_OP, UN_OP, OP, ASSIGMENT, KW
}

enum class TypeLex {
    DECIMAL, BOOLEAN
}

var NUMBERS = "0123456789"
var CHARS = "abcdefghijklmnopqrstuvwxyz"


val TYPES = mapOf(
    "Decimal" to 105,
    "Boolean" to 106)

var KEYWORDS =  TYPES + mapOf(
    "Begin" to 100,
    "End" to 101,
    "IF" to 102,
    "ELSE" to 103,
    "Var" to 104
    )

var UN_OP = mapOf("!" to 210)

var BIN_OP = mapOf(
    "&" to 200,
    "|" to 201,
    "^" to 202,
    "-" to 203,
    "+" to 204,
    "*" to 205,
    "/" to 206,
    ">" to 207,
    "<" to 208,
    "=" to 209
)

var OP = BIN_OP + UN_OP + mapOf(
    ":=" to 20
)

var BRACKETS = mapOf(
    "(" to 302,
    ")" to 303
)

var SEPARATORS = mapOf(
    ";" to 300,
    ":" to 301,
    "," to 302
)

var IDENT_CODE =  400

var LCONST = mapOf(
    "0" to 500,
    "1" to 501
)

var DCONST_CODE =  502

var ALL = KEYWORDS + OP + LCONST + BRACKETS + SEPARATORS