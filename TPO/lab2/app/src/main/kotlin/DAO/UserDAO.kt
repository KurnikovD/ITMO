package DAO

import kotliquery.Session
import kotliquery.queryOf
import module.User
import module.toUser


class UserDAO(private val session: Session) {

    fun find(username: String, password: String): User? {
        val queryString = "select * from lab2.user where user_name = ? and pass = ?"
        return session.run(queryOf(queryString, username, password).map(toUser).asSingle)
    }

    fun getAllUsers(): List<User> {
        val queryString = "select * from lab2.user"
        return session.run(queryOf(queryString).map(toUser).asList)
    }

    fun getUserByName(username: String): User? {
        val queryString = "select * from lab2.user where user_name = ?"
        return session.run(queryOf(queryString, username).map(toUser).asSingle)

    }

    fun setPermitToUserByUserName(username: String, permit: Int) {
        val queryString = "update lab2.user set permit = ? where user_name = ?"
        session.run(queryOf(queryString, permit, username).asUpdate)
    }

    fun deleteAccount(id: Int) {
        val queryString = "delete from lab2.user where id = ?"
        session.run(queryOf(queryString, id).asExecute)
    }

    fun insert(username: String, password: String) {
        val insertQuery = "insert into lab2.user (user_name, pass, permit) values (?, ?, ?)"
        session.run(queryOf(insertQuery, username, password, 0).asUpdate)
    }

    fun getUserById(userId: Int): String {
        val queryString = "select * from lab2.user where id = ?"
        return session.run(queryOf(queryString, userId).map(toUser).asSingle)!!.userName
    }

    fun getSession(): Session {
        return session
    }

    fun getIdByUserName(s: String): Int {
        val queryString = "select * from lab2.user where user_name = ?"
        return session.run(queryOf(queryString, s).map(toUser).asSingle)!!.id
    }

}
