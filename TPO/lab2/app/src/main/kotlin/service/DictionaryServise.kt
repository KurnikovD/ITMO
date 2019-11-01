package service

import DAO.WordDAO
import exeprion.WordExeption
import kotliquery.Session
import module.Post
import module.Pref
import module.Root
import module.Word

class DictionaryServise {
    private lateinit var wordDAO: WordDAO
    private lateinit var authService: AuthServise

    fun getWordsByRootName(root: String): List<Word> {
        val roots: List<Root> = wordDAO.getRootsByName(root)
        val words = mutableListOf<Word>()
        if (roots.isEmpty())
            throw WordExeption("Word with this root is not exist!")
        roots.forEach {
            val pref: List<Pref> = wordDAO.getPrefByRootID(it.id)
            val post: List<Post> = wordDAO.getPostByRootId(it.id)
            words.add(Word(pref, it, post))
        }
        words.sortBy { word -> word.count }
        return words
    }

    fun deleteWordById(id: Int?) {
        wordDAO.deleteWordById(id)
    }

    fun addWord(pref: List<String>, root: String, post: List<String>) {
        if (authService.getUser()?.permit == 2)
            throw WordExeption("Ban user could not add word!")
        var rootId: Int? = null
        if (root != "")
            rootId = wordDAO.insertRoot(root, authService.getUser()!!.id)
        if (rootId == null)
            throw WordExeption("Could not insert word!")

        if (pref.isNotEmpty())
            pref.forEach {
                wordDAO.insertPref(it, rootId)
            }

        if (post.isNotEmpty())
            post.forEach{
                wordDAO.insertPost(it, rootId)
            }
    }

    fun getUserWords(): List<Word> {
        val roots = wordDAO.getRootsByUserId(authService.getUser()!!.id)
        val words = mutableListOf<Word>()
        if (roots.isEmpty())
            throw WordExeption("User have not words!")
        roots.forEach {
            val pref: List<Pref> = wordDAO.getPrefByRootID(it.id)
            val post: List<Post> = wordDAO.getPostByRootId(it.id)
            words.add(Word(pref, it, post))
        }
        words.sortBy { word -> word.count }
        return words
    }

    fun getAllWords(): List<Word> {
        val roots: List<Root> = wordDAO.getAllRoots()
        val words = mutableListOf<Word>()
        if (roots.isEmpty())
            throw WordExeption("Dictionary is empty!")
        roots.forEach {
            val pref: List<Pref> = wordDAO.getPrefByRootID(it.id)
            val post: List<Post> = wordDAO.getPostByRootId(it.id)
            words.add(Word(pref, it, post))
        }
        words.sortBy { word -> word.count }
        return words
    }

    fun setAuthService(authService: AuthServise) {
        this.authService = authService
    }

    fun setWodrDAO(sessionDB: Session) {
        wordDAO = WordDAO(sessionDB)
    }
}