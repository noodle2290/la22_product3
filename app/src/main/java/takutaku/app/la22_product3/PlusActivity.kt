package takutaku.app.la22_product3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import takutaku.app.la22_product3.databinding.ActivityPlusBinding


class PlusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlusBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        val wordDao = db.wordDao()

        binding.saveButton.setOnClickListener {
            val word = Word(0,binding.titleEditText.text.toString(),binding.contentEditText.text.toString())
            wordDao.insertAll(word)
            val toMainActivityIntent = Intent(this,MainActivity::class.java)
            startActivity(toMainActivityIntent)
        }
    }
}