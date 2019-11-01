package service

import DAO.UserDAO
import DAO.WordDAO
import exeprion.WordExeption
import kotliquery.sessionOf
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.function.Executable
import java.math.BigInteger
import java.security.MessageDigest


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DictionaryServiseTest {
    private lateinit var wordDAO: WordDAO
    private  var dictionaryServise: DictionaryServise = DictionaryServise()
    private  var authService: AuthServise = AuthServise()

    @BeforeAll
    fun  init(){
        wordDAO = WordDAO(sessionOf("jdbc:postgresql:tpo", "postgres", "santa666"))
        authService.setSessionDB(wordDAO.getSession())
        dictionaryServise.setWodrDAO(wordDAO.getSession())
        dictionaryServise.setAuthService(authService)
        authService.login("santa", "santa".md5())
    }
    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    @Test
    fun getWordsByRootName() {
        val rootName = "ход"
        val words = dictionaryServise.getWordsByRootName(rootName)
        val roots = wordDAO.getRootsByName("ход")
        roots.forEach {
            val prefs = wordDAO.getPrefByRootID(it.id)
            val posts = wordDAO.getPostByRootId(it.id)
            assertAll("Find word by root name",
                Executable {assert(it.name == rootName)
                        assert(prefs.size == words.find { word -> it.id == word.root.id }?.prefs?.size)
                        assert(posts.size == words.find { word -> it.id == word.root.id }?.posts?.size)}
            )
        }
    }

    @Test
    fun deleteWordById() {
        val rootId = wordDAO.insertRoot("чит", authService.getUser()?.id)
        wordDAO.insertPref("про", rootId)
        wordDAO.insertPost("ать", rootId)

        dictionaryServise.deleteWordById(rootId)
        assert(wordDAO.getRootsByName("чит").find { it.id == rootId} == null)
    }

    @Test
    fun addWord() {
        val root = "test"
        val pref = listOf<String>("pre", "p")
        val post = listOf<String>("post", "st")
        dictionaryServise.addWord(pref, root, post)
        assert(wordDAO.getRootsByName(root).isNotEmpty())
        dictionaryServise.deleteWordById(wordDAO.getRootsByName(root).first().id)
    }

    @Test
    fun getUserWords() {
        authService.login("admin", "admin".md5())
        val words = dictionaryServise.getUserWords()
        val roots = wordDAO.getRootsByUserId(authService.getUser()!!.id)

        roots.forEach{
            val prefs = wordDAO.getPrefByRootID(it.id)
            val posts = wordDAO.getPostByRootId(it.id)
            assertAll(
                Executable {
                assert(words.find { word -> word.root.name == it.name } != null)
                assert(prefs.size == words.find { word -> it.id == word.root.id }?.prefs?.size)
                assert(posts.size == words.find { word -> it.id == word.root.id }?.posts?.size)}
            )
        }
    }

    @Test
    fun getAdminWords() {
        authService.login("santa", "santa".md5())
        val words = dictionaryServise.getUserWords()
        val roots = wordDAO.getRootsByUserId(authService.getUser()!!.id)

        roots.forEach{
            val prefs = wordDAO.getPrefByRootID(it.id)
            val posts = wordDAO.getPostByRootId(it.id)
            assertAll(
                Executable {
                    assert(words.find { word -> word.root.name == it.name } != null)},
                Executable {
                    assert(prefs.size == words.find { word -> it.id == word.root.id }?.prefs?.size)},
                Executable {
                    assert(posts.size == words.find { word -> it.id == word.root.id }?.posts?.size)}
            )
        }
    }
    @Test
    fun getAllWordsForAdmin() {
        authService.login("santa", "santa".md5())
        val words = dictionaryServise.getAllWords()
        val roots = wordDAO.getAllRoots()

        roots.forEach{
            val prefs = wordDAO.getPrefByRootID(it.id)
            val posts = wordDAO.getPostByRootId(it.id)
            assert(words.find { word -> word.root.name == it.name } != null)
            assert(prefs.size == words.find { word -> it.id == word.root.id }?.prefs?.size)
            assert(posts.size == words.find { word -> it.id == word.root.id }?.posts?.size)
        }
    }

    @Test
    fun getAllWordsForUser() {
        authService.login("admin", "admin".md5())
        val words = dictionaryServise.getAllWords()
        val roots = wordDAO.getAllRoots()

        roots.forEach{
            val prefs = wordDAO.getPrefByRootID(it.id)
            val posts = wordDAO.getPostByRootId(it.id)
            assert(words.find { word -> word.root.name == it.name } != null)
            assert(prefs.size == words.find { word -> it.id == word.root.id }?.prefs?.size)
            assert(posts.size == words.find { word -> it.id == word.root.id }?.posts?.size)
        }
    }

    @Test
    fun checkSomePrefs(){
        authService.login("santa", "santa".md5())
        val prefs = listOf<String>("first", "second", "third")
        val root = "prefs"
        dictionaryServise.addWord(prefs, root, List<String>(0){""})
        val id = wordDAO.getRootsByName("prefs").first().id
        assert(wordDAO.getPrefByRootID(id).size == prefs.size)
        wordDAO.deleteWordById(id)
    }

    @Test
    fun checkSomePosts(){
        authService.login("santa", "santa".md5())
        val posts = listOf<String>("first", "second", "third")
        val root = "posts"
        dictionaryServise.addWord(listOf(), root, posts)
        val id = wordDAO.getRootsByName("posts").first().id
        assert(wordDAO.getPostByRootId(id).size == posts.size)
        wordDAO.deleteWordById(id)
    }

    @Test
    fun checkAddWordByBanUser(){
        authService.login("banUser", "password".md5())
        try{
            dictionaryServise.addWord(listOf("tr"), "root", listOf("rtr"))
        }catch  (e: WordExeption){
            assert(e.message == "Ban user could not add word!")
        }
    }

    @Test
    fun checkSorting(){
        val words = dictionaryServise.getAllWords()
        assert(words.first().count <= words[1].count)
    }
}