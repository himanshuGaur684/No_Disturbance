package gaur.himanshu.nodisturbance.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gaur.himanshu.nodisturbance.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        fun getInstance(context:Context):AppDatabase{
            return Room.databaseBuilder(context,AppDatabase::class.java,"app_db").build()
        }
    }

    abstract fun getTaskDAO():TaskDAO

}
