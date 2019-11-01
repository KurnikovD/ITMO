package service

import DAO.UserDAO
import exeprion.UserException
import kotliquery.Session
import kotliquery.sessionOf
import module.User

class AuthServise {
    private lateinit var userDAO: UserDAO
    private var user: User? = null

    fun login(username: String, password: String){
        try {
            user = userDAO.find(username, password)!!
        }catch(e: Exception){
            throw UserException("Incorrect userName or Password!")
        }

    }

    fun grand(username: String) {
        if (user!!.permit != 1)
            throw UserException("User is not administrator!")
        if (user!!.userName == username)
            throw UserException("You could not ban yourself")
        if (userDAO.getUserByName(username) == null)
            throw UserException("User is not exist")
        if (userDAO.getUserByName(username)!!.permit == 1)
            userDAO.setPermitToUserByUserName(username, 0)
        else
            userDAO.setPermitToUserByUserName(username, 1)
    }

    fun unban(username: String) {
        if (user!!.permit != 1)
            throw UserException("User is not administrator!")
        if (userDAO.getUserByName(username) == null)
            throw UserException("User is not exist")
        userDAO.setPermitToUserByUserName(username, 0)
    }

    fun ban(username: String) {
        if (user!!.permit != 1)
            throw UserException("User is not administrator!")
        if (user!!.userName == username)
            throw UserException("You could not ban yourself")

        if (userDAO.getUserByName(username) == null)
            throw UserException("User is not exist")
        if (userDAO.getUserByName(username)!!.permit == 1)
            throw UserException("You could ban administrator")
        userDAO.setPermitToUserByUserName(username, 2)
    }

    fun viewAllUsers(): List<User> {
        if (user!!.permit != 1)
            throw UserException("User is not administrator!")
        return userDAO.getAllUsers()
    }

    fun viewUserByName(username: String): User? {
        if (user!!.permit != 1)
            throw UserException("User is not administrator!")
        return userDAO.getUserByName(username)
    }

    fun deleteAccount() {
        if (user == null) {
            throw UserException("Unable to delete account: user is not logged in.");
        }
        userDAO.deleteAccount(user!!.id)
        logout()
    }

    fun logout() {
        user = null
    }

    fun registration(username: String, password: String) {
        if (userDAO.getUserByName(username) != null)
            throw UserException("User already exist!")
        userDAO.insert(username, password)
    }

    fun getUsernameById(userId: Int): String {
        return userDAO.getUserById(userId)
    }

    fun setSessionDB(sessionDB: Session) {
        userDAO = UserDAO(sessionDB)
    }

    fun getUser(): User? {
            return user

    }

    fun isLogind():Boolean{
        return user != null
    }

    fun isAdmin(): Boolean{
       return user?.permit == 1
    }

    fun getUserByName(s: String): User? {
        return userDAO.getUserByName(s)
    }

    fun validUserNameAndPassword(username: String, password: String): Boolean {
        if(username.count() < 4)
            throw UserException("Username so short")
        if(username.count() > 16)
            throw  UserException("Username so long")

        if(password.count() < 4)
            throw UserException("Password so short")
        if(password.count() > 16)
            throw  UserException("Password so long")
        return true
    }
}