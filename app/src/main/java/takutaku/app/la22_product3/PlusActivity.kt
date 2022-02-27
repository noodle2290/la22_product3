package takutaku.app.la22_product3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
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

        val id:Int = intent.getIntExtra("ID",0)
        val wordDao = db.wordDao()
        val word = wordDao.getWord(id)
        val toMainIntent = Intent(this, MainActivity::class.java)
        val toDetailIntent = Intent(this, DetailActivity::class.java)

        if(id == 0) {
            binding.saveButton.setOnClickListener {
                val word = Word(
                    0,
                    binding.titleEditText.text.toString(),
                    binding.contentEditText.text.toString()
                )
                wordDao.insert(word)
                startActivity(toMainIntent)
            }
        }else{
            binding.deleteButton.isVisible = true
            binding.titleEditText.setText(word.title)
            binding.contentEditText.setText(word.content)
            binding.saveButton.setOnClickListener {
                val word = Word(
                    id,
                    binding.titleEditText.text.toString(),
                    binding.contentEditText.text.toString()
                )
                wordDao.update(word)
                toDetailIntent.putExtra("ID",word.id)
                startActivity(toDetailIntent)
            }
        }
    }
}