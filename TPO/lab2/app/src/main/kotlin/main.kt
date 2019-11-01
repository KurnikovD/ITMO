import exeprion.UserException
import exeprion.WordExeption
import kotliquery.sessionOf
import module.User
import module.Word
import service.AuthServise
import service.DictionaryServise
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import kotlin.system.exitProcess

private val authService: AuthServise = AuthServise()
private val dictionaryServise: DictionaryServise = DictionaryServise()
private val scaner = Scanner(System.`in`)


fun main() {
    val sessionDB = sessionOf("jdbc:postgresql:tpo", "postgres", "santa666")
    authService.setSessionDB(sessionDB)
    dictionaryServise.setAuthService(authService)
    dictionaryServise.setWodrDAO(sessionDB)
    handleMainMenu()
}

fun printMainMenu(){
    println(" - Main menu - \n" +
            "1. Show all words;\n" +
            "2. Find word by root;\n" +
            "3. LogIn;\n" +
            "4. Registration;\n" +
            "0. Exit;\n" +
            "Choose menu item: ")
}
fun handleMainMenu() {

    var point: Int
    loop@ do{
        printMainMenu()
        try {
            point = scaner.nextInt()
        }catch (e : InputMismatchException){
            point = -1
            scaner.nextLine()
            continue
        }

        when(point){
            1 -> handleShowAllWords()
            2 -> handleFindWordByRoot()
            3 -> {
                handleLogin()
            }
            4 -> handleRegistration()
            0 -> exitProcess(0)
            else -> println("Out from menu item")
        }
    }while(point != 0)
}
fun printLoginMenu(){
    println(" - User menu - \n" +
            "1. Show All Words;\n" +
            "2. Find word by root;\n" +
            "3. My words;\n" +
            "4. Add word;\n" +
            "5. Delete word;\n" +
            "6. Logout;\n" +
            "7. Delete account\n" +
            "0. Exit;\n" +
            "Choose menu item: ")
}
fun handleLoginMenu() {

    var point: Int
    loop@ do{
        printLoginMenu()
        try {
            point = scaner.nextInt()
        }catch (e : InputMismatchException){
            point = -1
            scaner.nextLine()
            continue
        }

        when(point){
            1 -> handleShowAllWords()
            2 -> handleFindWordByRoot()
            3 -> handleShowMyWords()
            4 -> handleAddWord()
            5 -> handleDeleteWord()
            6 -> handleLogout()
            7 -> handleDeleteAccount()
            0 -> exitProcess(0)
            else -> println("Out from menu item")
        }
    }while(point != 6)
}
fun printAdministratorMenu(){
    println("- Administrator menu - \n" +
            "1. show all words;\n" +
            "2. Find word by root;\n" +
            "3. My words;\n" +
            "4. Add word;\n" +
            "5. Delete word;\n" +
            "6. View all user;\n"+
            "7. View users by name;\n" +
            "8. Ban user;\n" +
            "9. Unban user;\n" +
            "10. Grand user;\n" +
            "11. Logout;\n" +
            "0. Exit;\n" +
            "Choose menu item: ")
}
fun handleAdministratorMenu(){
    var point: Int
    do{
        printAdministratorMenu()
        try {
            point = scaner.nextInt()
        }catch (e : InputMismatchException){
            point = -1
            scaner.nextLine()
            continue
        }

        when(point){
            1 -> handleShowAllWords()
            2 -> handleFindWordByRootAdministrator()
            3 -> handleShowMyWords()
            4 -> handleAddWord()
            5 -> handleDeleteWord()
            6 -> handleViewAllUser()
            7 -> handleViewUserByName()
            8 -> handleBanUser()
            9 -> handleUnbanUser()
            10 -> handleFindUserByName()
            11 -> handleLogout()
            0 -> exitProcess(0)
            else -> println("Out from menu item")
        }
    }while(point != 11)
}
fun handleFindUserByName() {

    println(" - Find user by username Admin - ")
    print("Input username: ")
    val userName = readLine().toString()
    try{
        val user: User = authService.getUserByName(userName) ?: throw UserException("User in not exist")
        print("${user.id} - ${user.userName} - ${user.permit}")

    }catch (e: UserException) {
        println(e)
    }
}
fun handleFindWordByRootAdministrator() {

    println(" - Find words by root Admin - ")
    print("Input root: ")
    val root = readLine().toString()
    try{
        val words : List<Word> = dictionaryServise.getWordsByRootName(root)
        words.forEach {
            print("${authService.getUsernameById(it.root.userId)}: ")
            printWorld(it)
        }
        println()

    }catch (e: UserException) {
        println(e)
    }

}
fun printBanUserMenu(){
    println(" - User menu -\n" +
            "YOU ARE IN BLACK LIST\n" +
            "1. Show all words;\n" +
            "2. Find words;\n" +
            "3. Show my words;\n" +
            "4. Delete words;" +
            "5. Logout;\n" +
            "6. Delete Account;\n" +
            "0. Exit;\n" +
            "Choose menu item: ")
}
fun handleBanUserMenu(){

    var point: Int
    loop@ do{
        printLoginMenu()
        try {
            point = scaner.nextInt()
        }catch (e : InputMismatchException){
            point = -1
            scaner.nextLine()
            continue
        }

        when(point){
            1 -> handleShowAllWords()
            2 -> handleFindWordByRoot()
            3 -> handleShowMyWords()
            4 -> handleDeleteWord()
            5 -> handleLogout()
            6 -> handleDeleteAccount()
            0 -> exitProcess(0)
            else -> println("Out from menu item")
        }
    }while(point != 5)
}
fun handleGrandUser() {

    println(" - Grand User - ")
    print("Username: ")
    val username = readLine().toString()
    try{
        authService.unban(username)
        println("User $username was unban!")
    }catch (e: UserException) {
        println(e)
    }
}
fun handleUnbanUser() {

    println(" - Unban User -")
    print("Username: ")
    val username = readLine().toString()
    try{
        authService.grand(username)
        println("Grand success!")
    }catch (e: UserException) {
        println(e)
    }
}
fun handleBanUser() {

    println(" - Ban User -")
    print("Username: ")
    val username = readLine().toString()
    try{
        authService.ban(username)
        println("User $username was ban!")
    }catch (e: UserException) {
        println(e)
    }
}
fun handleViewAllUser() {

    println(" - View Users -")
    try{
        val users = authService.viewAllUsers()
        print(" - id - |   - userName - ")
        users.forEach {
            print("\n %6s |".format(it.id))
            println(" %16s |  ".format(it.userName))
        }
    }catch (e: UserException) {
        println(e)
    }
}
fun handleViewUserByName() {
    println(" - View user by name -")
    print("Username: ")
    val username = readLine().toString()
    try{
        authService.viewUserByName(username)
        println("Grand success!")
    }catch (e: UserException) {
        println(e)
    }
}
fun handleDeleteAccount() {

    println(" - Delete account -")
    print("Are you sure? ")
    val answer = readLine().toString()
    if(answer == "y" || answer == "yes")
        try{
            authService.deleteAccount()
            authService.logout()
            println("Account delete!")
        }catch (e: UserException) {
            println(e)
        }
}
fun handleLogout() {

    println(" - Logout -")
    print("Are you sure? ")
    val answer = readLine().toString()
    if(answer == "y" || answer == "yes")
        try{
            authService.logout()
        }catch (e: UserException) {
            println(e)
        }
}
private fun printWorld(word: Word){
//    print("   - userName -   | - id - |      - word -")
        print("${word.root.id}. ")
        word.prefs.forEach {
            print(it.name + '-')
        }
        print(" ${word.root.name} ")
        word.posts.forEach {
            print('-' + it.name)
        }
    println()
}
fun handleDeleteWord() {

    println(" - Delete word -")
    print("Input root of word")
    val root = readLine().toString()
    try{
        val words = dictionaryServise.getWordsByRootName(root)
        words.forEach{printWorld(it)}
        println()
        print("Choose id: ")
        val id = readLine()?.toInt() ?: throw WordExeption("Id is not a number")
        words.forEach {
            if(id == it.root.id) {
                dictionaryServise.deleteWordById(it.root.id)
                println("Word success delete!")
                return
            }
        }
    }catch (e: WordExeption) {
        println(e)
    }
}
fun handleAddWord() {

    println(" - Delete word -")
    val pref = mutableListOf<String>()
    val root: String
    val post = mutableListOf<String>()
    var input = ""
    do {
        print("Input prefix: ")
        input = readLine().toString()
        if(input != "")
            pref.addAll(input.split(' '))
    }while(input != "")

    print("Input root: ")
    root = readLine().toString()

    do {
        print("Input postfix: ")
        input = readLine().toString()
        if(input != "")
            post.addAll(input.split(' '))
    }while(input != "")
    try {
        dictionaryServise.addWord(pref, root, post)
    }catch (e: WordExeption){
        println(e)
    }
}
fun handleShowMyWords() {

    println(" - My words -")
    try {
        val words = dictionaryServise.getUserWords()
        words.forEach {
            printWorld(it)
        }
        println()
    }catch (e: WordExeption){
        println(e)
    }
}
fun handleRegistration() {

    println(" - Registration - ")
    print("Username: ")
    val username = readLine().toString()
    print("password: ")
    val password = readLine().toString()
    try{
        authService.validUserNameAndPassword(username, password)
        authService.registration(username, password.md5())
        println("Registration success")
    }catch (e: UserException) {
        println(e)
    }
}
private fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}
fun handleLogin() {

    println(" - Login - ")
    print("Username: ")
    val username = readLine().toString()
    print("password: ")
    val password = readLine().toString().md5()
    try{
        authService.login(username, password)
        println("Login success")
        if (authService.getUser()?.permit == 1)
            handleAdministratorMenu()
        if (authService.getUser()?.permit == 2)
            handleBanUserMenu()
        if(authService.getUser()?.permit == 0)
            handleLoginMenu()
    }catch (e: UserException) {
        println(e)
    }
}
fun handleShowAllWords(){

    println(" - All words - ")
    try{
        val words : List<Word> = dictionaryServise.getAllWords()
        words.forEach { printWorld(it) }
        println()
    }catch (e: UserException) {
        println(e)
    }
}
fun handleFindWordByRoot(){

    println(" - Find words by root - ")
    print("Input root: ")
    val root = readLine().toString()
    try{
        val words : List<Word> = dictionaryServise.getWordsByRootName(root)
        words.forEach {
            printWorld(it)
        }
        println()
    }catch (e: UserException) {
        println(e)
    }
}
