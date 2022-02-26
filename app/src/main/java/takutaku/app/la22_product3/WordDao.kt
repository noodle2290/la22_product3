package takutaku.app.la22_product3

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    fun getAll(): List<Word>

    @Query("SELECT * FROM words WHERE id IN (:wordIds)")
    fun loadAllByIds(wordIds: IntArray): List<Word>

    @Query("SELECT * FROM words WHERE title LIKE :first AND " +
            "content LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Word

    @Insert
    fun insertAll(vararg words: Word)

    @Delete
    fun delete(word: Word)
}