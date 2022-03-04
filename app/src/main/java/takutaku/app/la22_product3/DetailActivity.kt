package takutaku.app.la22_product3

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.room.Room
import takutaku.app.la22_product3.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val wordDao = WordApplication.db.wordDao()

    private lateinit var word:Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val id:Int = intent.getIntExtra(Constants.SELECTED_MEMO_ID,0)

        word = wordDao.getWord(id)

        binding.toolbarTitle.text = word.title
        binding.contentDetailTextView.text = word.content

//        toolbarをActionBarとして扱う
        setSupportActionBar(binding.toolbar)
//        ActionBarの文字を削除
        supportActionBar!!.setDisplayShowTitleEnabled(false)
//        ActionBarに戻るボタンを実装
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

        binding.editIntentButton.setOnClickListener {
            Intent(PlusActivity(),word.id)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.detail_toolbar_option_button, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                Intent(MainActivity())
                true
            }
            R.id.delete -> {
                Toast.makeText(applicationContext, word.title + Constants.DELETE_MEMO_DIALOG, Toast.LENGTH_SHORT).show()
                wordDao.delete(word)
                Intent(MainActivity())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun Intent(activity: Activity, vararg ids:Int){
        val ActivityIntent = Intent(applicationContext,activity::class.java)
        for(i in ids) {
            ActivityIntent.putExtra(Constants.SELECTED_MEMO_ID, i)
        }
        startActivity(ActivityIntent)
    }
}