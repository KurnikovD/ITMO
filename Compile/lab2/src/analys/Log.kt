package analys

import java.io.File

class Log {

    var log = mutableListOf<String>()
    var errLog = mutableListOf<String>()
    val fileErr = "outputR.k"
    val fileO = "output.k"

    fun addLexeme(lexs: String, token: IntArray){
        log.add("(${ALL[lexs]}, $lexs, $token)")
    }

    fun addError(errString: String, row: Int, col: Int){
        log.add("ERROR in $row:$col - $errString")
    }

    fun addIdentify(id: Int, token: IntArray){
        errLog.add("($IDENT_CODE, '0x$id', $token)")
    }
    fun addDConst(value: Int, token: IntArray){
        errLog.add("($DCONST_CODE, '0x$value', $token)")
    }

    fun addLConst(value: String, token: IntArray){
        errLog.add("(${LCONST[value]}, '0x$value', $token)")
    }

    fun writeLog(){
        for (line in log)
            File(fileO).writeText(line)
        File(fileErr).writeText(errLog.toString())
    }
}