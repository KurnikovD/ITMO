package module

import kotliquery.Row

data class Word(
    val prefs: List<Pref>,
    val root: Root,
    val posts: List<Post>,
    val count: Int = prefs.size + posts.size
)

data class Root(
    val id: Int,
    val name: String,
    val userId: Int
)
val toRoot: (Row) -> Root = { row ->
    Root(
        row.int("id"),
        row.string("name"),
        row.int("user_id")
    )
}

data class Pref(
    val name: String,
    val root_id: Int
)
val toPref: (Row) -> Pref = { row ->
    Pref(
        row.string("name"),
        row.int("root_id")
    )

}

data class Post(
    val name: String,
    val root_id: Int
)
val toPost: (Row) -> Post = { row ->
    Post(
        row.string("name"),
        row.int("root_id")
    )
}