package takutaku.app.la22_product3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.room.Room
import takutaku.app.la22_product3.databinding.ActivityPlusBinding


class PlusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlusBinding

    private val wordDao = WordApplication.db.wordDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlusBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val id:Int = intent.getIntExtra(Constants.SELECTED_MEMO_ID,0)
        val word = wordDao.getWord(id)

        if(id == 0) {
            binding.saveButton.setOnClickListener {
                val word = Word(0, binding.titleEditText.text.toString(), binding.contentEditText.text.toString())
                wordDao.insert(word)
                Intent(MainActivity())
            }
        }else{
            binding.titleEditText.setText(word.title)
            binding.contentEditText.setText(word.content)
            binding.saveButton.setOnClickListener {
                val word = Word(id, binding.titleEditText.text.toString(), binding.contentEditText.text.toString())
                wordDao.update(word)
                Intent(DetailActivity(),word.id)
            }
        }
    }

    fun Intent(activity: Activity, vararg ids:Int){
        val activityIntent = Intent(applicationContext,activity::class.java)
        for(i in ids) {
            activityIntent.putExtra(Constants.SELECTED_MEMO_ID, i)
        }
        startActivity(activityIntent)
    }
}