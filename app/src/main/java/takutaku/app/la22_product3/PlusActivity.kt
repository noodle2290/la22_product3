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

        val titleName:String? = intent.getStringExtra("TITLE")
        val contentName:String? = intent.getStringExtra("MEMO")
        val id:Int = intent.getIntExtra("ID",0)
        val wordDao = db.wordDao()
        val toMainActivityIntent = Intent(this, MainActivity::class.java)

        if(titleName == null) {
            binding.deleteButton.isInvisible = true
            binding.saveButton.setOnClickListener {
                val word = Word(
                    0,
                    binding.titleEditText.text.toString(),
                    binding.contentEditText.text.toString()
                )
                wordDao.insert(word)
                startActivity(toMainActivityIntent)
            }
        }else{
            binding.deleteButton.isVisible = true
            binding.titleEditText.setText(titleName)
            binding.contentEditText.setText(contentName)
            binding.saveButton.setOnClickListener {
                val word = Word(
                    id,
                    binding.titleEditText.text.toString(),
                    binding.contentEditText.text.toString()
                )
                wordDao.update(word)
                startActivity(toMainActivityIntent)
            }
            binding.deleteButton.setOnClickListener {
                val word = Word(
                    id,
                    titleName,
                    contentName
                )
                wordDao.delete(word)

                startActivity(toMainActivityIntent)
            }
        }
    }
}