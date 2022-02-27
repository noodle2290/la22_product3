package takutaku.app.la22_product3

import androidx.room.*

@Dao
interface WordDao {
    @Insert
    fun insert(word : Word)

    @Update
    fun update(word : Word)

    @Delete
    fun delete(word : Word)

    @Query("delete from words")
    fun deleteAll()

    @Query("select * from words")
    fun getAll(): List<Word>

    @Query("select * from words where id = :id")
    fun getWord(id: Int): Word
}