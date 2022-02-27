package takutaku.app.la22_product3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.room.Room
import takutaku.app.la22_product3.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var toMainIntent:Intent
    private lateinit var toPlusIntent:Intent
    private lateinit var wordDao:WordDao
    private lateinit var word:Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        toMainIntent = Intent(this, MainActivity::class.java)
        toPlusIntent = Intent(this, PlusActivity::class.java)

//        roomのインスタンス生成
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        //        Daoのインスタンス生成
        wordDao = db.wordDao()
        val id:Int = intent.getIntExtra("ID",0)
        word = wordDao.getWord(id)
        binding.toolbarTitle.text = word.title
        binding.contentDetailTextView.text = word.content

//        toolbarをActionBarとして扱う
        setSupportActionBar(binding.toolbar)
//        ActionBarの文字を削除
        supportActionBar!!.setDisplayShowTitleEnabled(false)
//        ActionBarに戻るボタンを実装
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.editIntentButton.setOnClickListener {
            toPlusIntent.putExtra("ID",word.id)
            startActivity(toPlusIntent)
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
                startActivity(toMainIntent)
                true
            }
            R.id.delete -> {
                wordDao.delete(word)
                startActivity(toMainIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}