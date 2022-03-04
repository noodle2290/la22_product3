package takutaku.app.la22_product3

import android.app.Application
import androidx.room.Room

class WordApplication: Application() {
    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, "database-name")
            .allowMainThreadQueries()
            .build()
    }
}