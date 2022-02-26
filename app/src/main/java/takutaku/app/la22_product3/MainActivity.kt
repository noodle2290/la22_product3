package takutaku.app.la22_product3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import takutaku.app.la22_product3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        val wordDao = db.wordDao()
        val words: List<Word> = wordDao.getAll()

        if(words != null) {
            binding.titleTextView.text = words.last().title
            binding.contentTextView.text = words.last().content
        }

        binding.fab.setOnClickListener {
            val toPlusActivityIntent = Intent(this,PlusActivity::class.java)
            startActivity(toPlusActivityIntent)
        }
    }
}