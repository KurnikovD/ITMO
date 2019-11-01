package service

import DAO.UserDAO
import exeprion.UserException
import kotliquery.sessionOf
import module.User
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.math.BigInteger
import java.security.MessageDigest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AuthServiseTest {
    private lateinit var userDAO: UserDAO
    private  var authService: AuthServise = AuthServise()

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    @BeforeAll
    fun  init(){
        userDAO = UserDAO(sessionOf("jdbc:postgresql:tpo", "postgres", "santa666"))
        authService.setSessionDB(userDAO.getSession())
    }


    @Test
    fun login() {
        authService.login("admin", "admin".md5())
        assert(authService.getUser()?.id == 6)
    }

    @Test
    fun grand() {
        authService.login("santa", "santa".md5())
        authService.grand("admin")
        assert(authService.getUserByName("admin")?.permit == 1)
        authService.grand("admin")
    }

    @Test
    fun `test grant to yourself`(){
        authService.login("santa", "santa".md5())
        try {
            authService.grand("santa")
        }catch (e: UserException){
            assert(e.message == "You could not ban yourself")
        }
    }

    @Test
    fun testUnexeptebleUserName(){
        authService.login("santa", "santa".md5())
        try {
            authService.grand("unxept")
        }catch (e: UserException){
            assert(e.message == "User is not exist")
        }

    }

    @Test
    fun resrAnAdminUserGrant(){
        authService.login("admin", "admin".md5())
        try {
            authService.grand("commonUser")
        }catch (e: UserException){
            assert(e.message == "User is not administrator!")
        }
    }

    @Test
    fun unban() {
        authService.login("santa", "santa".md5())
        userDAO.insert("userForBan", "banme".md5())
        userDAO.setPermitToUserByUserName("userForBan", 2)
        authService.unban("userForBan")
        assert(authService.getUserByName("userForBan")?.permit == 0)
        userDAO.deleteAccount(userDAO.getIdByUserName("userForBan"))
    }

    @Test
    fun checkPermitforUnbun(){
        authService.login("admin", "admin".md5())
        userDAO.insert("userForBan", "banme".md5())
        userDAO.setPermitToUserByUserName("userForBan", 2)
        try {
            authService.unban("userForBan")
        }catch (e: UserException){
            assert(e.message == "User is not administrator!")
            userDAO.deleteAccount(userDAO.getIdByUserName("userForBan"))
        }
    }

    @Test
    fun chackBanYourself(){
        authService.login("santa", "santa".md5())
        try {
            authService.ban("santa")
        }catch (e: UserException){
            assert(e.message == "You could not ban yourself")
        }
    }

    @Test
    fun checkBanAdministartor(){
        authService.login("santa1", "santa1".md5())
        try {
            authService.ban("santa")
        }catch (e: UserException){
            assert(e.message == "You could ban administrator")
        }
    }

    @Test
    fun checkPermitforbun(){
        authService.login("admin", "admin".md5())
        userDAO.insert("userForBan", "banme".md5())
        userDAO.setPermitToUserByUserName("userForBan", 2)
        try {
            authService.ban("userForBan")
        }catch (e: UserException){
            assert(e.message == "User is not administrator!")
            userDAO.deleteAccount(userDAO.getIdByUserName("userForBan"))
        }
    }

    @Test
    fun ban() {
        authService.login("santa", "santa".md5())
        userDAO.insert("userForBan", "banme".md5())
        authService.ban("userForBan")
        assert(authService.getUserByName("userForBan")?.permit == 2)
        userDAO.deleteAccount(userDAO.getIdByUserName("userForBan"))
    }

    @Test
    fun viewAllUsers() {
        val usersD: List<User> = userDAO.getAllUsers()
        val users: List<User> = authService.viewAllUsers()
        assert(users.size == usersD.size)
    }

    @Test()
    fun checkGetAllUserForCommonUser(){
        authService.login("admin", "admin".md5())
        try{
             authService.viewAllUsers()
        }catch (e: UserException){
            assert(e.message == "User is not administrator!")
        }
    }

    @Test
    fun viewUserByName() {
        val username = "admin"
        val userD: User? = userDAO.getUserByName(username)
        val user = authService.getUserByName(username)
        if (userD != null) {
            if (user != null) {
                assert(userD.id == user.id)
            }
        }
        assert(user != null)
    }

    @Test
    fun deleteAccount() {
        userDAO.insert("userForDelete", "deleteme".md5())
        authService.login("userForDelete", "deleteme".md5())
        authService.deleteAccount()
        assert(!authService.isLogind())
    }

    @Test
    fun logout() {
        authService.login("admin", "admin".md5())
        authService.logout()
        assert(!authService.isLogind())
    }

    @Test
    fun registration() {
        authService.registration("newUser", "pass".md5())
        assert(userDAO.find("newUser", "pass".md5()) != null)
        userDAO.deleteAccount(userDAO.getIdByUserName("newUser"))
    }

    @Test
    fun chechRegistretedUser(){
        try {
            authService.registration("admin", "admin".md5())
        }catch (e: UserException){
            assert(e.message == "User already exist!")
        }
    }

    @Test
    fun getUsernameById() {
        val user = authService.getUsernameById(6)
        val userD = userDAO.getUserById(6)
        assert(user == userD)
    }

    @Test
    fun checkUserLogin() {
        authService.login("commonUser", "pass".md5())
        assert(authService.isLogind())

    }
    @Test
    fun checkAdmin() {
        authService.login("santa", "santa".md5())
        assert(authService.isAdmin())
    }

    @Test
    fun incorrectLogin(){
        try {
            authService.login("blabla", "bla".md5())
        }catch (e: UserException) {
            assert(e.message == "Incorrect userName or Password!")
        }
    }

    @Test
    fun checkLengthUsernameAndPassword(){
        assert(authService.validUserNameAndPassword("santa", "santa"))
    }

    @Test
    fun checkShortLengthUsername(){
        try{
            authService.validUserNameAndPassword("san", "santa")
        }catch (e: UserException) {
            assert(e.message == "Username so short")
        }
    }
    @Test
    fun checkLongLengthUsername(){
        try{
            authService.validUserNameAndPassword("santaclownisthebestclown", "santa")
        }catch (e: UserException) {
            assert(e.message == "Username so long")
        }
    }
    @Test
    fun checkShortLengthPassword(){
        try{
            authService.validUserNameAndPassword("santa", "san")
        }catch (e: UserException) {
            assert(e.message == "Password so short")
        }
    }
    @Test
    fun checkLongLengthPassword(){
        try{
            authService.validUserNameAndPassword("santa", "santaclownisthebestclown")
        }catch (e: UserException) {
            assert(e.message == "Password so long")
        }
    }
}
