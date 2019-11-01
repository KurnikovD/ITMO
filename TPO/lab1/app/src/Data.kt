data class ResultString(
    val exception : String,
    val methodName : String = "",
    val description : String = ""
)

data class FindAndClickElement(
    val name: String,
    val before : String,
    val find : String,
    val after: String,
    val exitType : String,
    val exitString : String
)

data class action(
    val type: Actions,
    val element: String,
    val x: Int = 0,
    val string: String = ""
)