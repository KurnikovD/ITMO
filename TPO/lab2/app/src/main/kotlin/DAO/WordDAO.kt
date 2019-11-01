package DAO

import kotliquery.Session
import kotliquery.queryOf
import module.*

class WordDAO (private val session: Session){
    fun getRootsByName(root: String): List<Root> {
        val queryString = "select * from lab2.root where name = ?"
        return session.run(queryOf(queryString, root).map(toRoot).asList)
    }

    fun getPrefByRootID(id: Int): List<Pref> {
        val queryString = "select * from lab2.pref where root_id = ?"
        return session.run(queryOf(queryString, id).map(toPref).asList)
    }

    fun getPostByRootId(id: Int): List<Post> {
        val queryString = "select * from lab2.post where root_id = ?"
        return session.run(queryOf(queryString, id).map(toPost).asList)
    }

    fun deleteWordById(id: Int?) {
        val queryString = "delete from lab2.root where id = ?"
        session.run(queryOf(queryString, id).asExecute)
    }

    fun insertRoot(root: String, userId: Int?): Int? {
        val insertQueryRoot: String = "insert into lab2.root (name, user_id) values (?, ?) RETURNING id;"
        return session.single(queryOf(insertQueryRoot, root, userId)){ row -> row.int("id")}
    }

    fun insertPref(name: String, rootId: Int?) {
        val insertQueryPref: String = "insert into lab2.pref (name, root_id) values (?, ?)"
        session.run(queryOf(insertQueryPref, name, rootId).asUpdate)
    }

    fun insertPost(name: String, rootId: Int?) {
        val insertQueryPref: String = "insert into lab2.post (name, root_id) values (?, ?)"
        session.run(queryOf(insertQueryPref, name, rootId).asUpdate)
    }

    fun getRootsByUserId(userId: Int): List<Root> {
        val queryString = "select * from lab2.root where user_id = ?"
        val i =session.run(queryOf(queryString, userId).map(toRoot).asList)
        return i
    }

    fun getAllRoots(): List<Root> {
        val queryString = "select * from lab2.root"
        return session.run(queryOf(queryString).map(toRoot).asList)
    }

    fun getSession(): Session {
        return session
    }

}