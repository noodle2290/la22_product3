package takutaku.app.la22_product3

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import takutaku.app.la22_product3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val wordDao = WordApplication.db.wordDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
//        roomのデータを全て取得
        val words: List<Word> = wordDao.getAll()
//        OnClickListenerを引数としてWordAdapterのインスタンス生成
        val wordAdapter = WordAdapter(
            OnClickListener { word ->
                Intent(DetailActivity(),word.id)
            }
        )
        wordAdapter.submitList(words)
        binding.recyclerView.adapter = wordAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.fab.setOnClickListener {
            Intent(PlusActivity())
        }
    }

    fun Intent(activity: Activity,vararg ids:Int){
        val activityIntent = Intent(applicationContext,activity::class.java)
        for(i in ids) {
            activityIntent.putExtra(Constants.SELECTED_MEMO_ID, i)
        }
        startActivity(activityIntent)
    }
}