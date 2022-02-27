package takutaku.app.la22_product3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import takutaku.app.la22_product3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

//        roomのインスタンス生成
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

//        Daoのインスタンス生成
        val wordDao = db.wordDao()
//        roomのデータを全て取得
        val words: List<Word> = wordDao.getAll()
//        OnClickListenerを引数としてWordAdapterのインスタンス生成
        val wordAdapter = WordAdapter(
            OnClickListener { word ->
                val toPlusIntent = Intent(this,PlusActivity::class.java)
                toPlusIntent.putExtra("TITLE",word.title)
                toPlusIntent.putExtra("MEMO",word.content)
                toPlusIntent.putExtra("ID",word.id)
                startActivity(toPlusIntent)
            }
        )
        wordAdapter.submitList(words)
        binding.recyclerView.adapter = wordAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.fab.setOnClickListener {
            val toPlusActivityIntent = Intent(this,PlusActivity::class.java)
            startActivity(toPlusActivityIntent)
        }
    }
}