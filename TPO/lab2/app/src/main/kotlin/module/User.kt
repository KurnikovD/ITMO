package module

import kotliquery.Row

data class User(
    var id: Int,
    val userName: String,
    val password: String,
    val permit: Int
)

val toUser : (Row) -> User = { row ->
    User(
        row.int("id"),
        row.string("user_name"),
        row.string("pass"),
        row.int("permit")
    )
}