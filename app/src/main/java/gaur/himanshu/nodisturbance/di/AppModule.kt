package gaur.himanshu.nodisturbance.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gaur.himanshu.nodisturbance.repository.TaskRepository
import gaur.himanshu.nodisturbance.room.AppDatabase
import gaur.himanshu.nodisturbance.room.TaskDAO
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideTaskDAO(appDatabase: AppDatabase): TaskDAO {
        return appDatabase.getTaskDAO()
    }

    @Provides
    fun provideTaskRepository(taskDAO: TaskDAO,firestore: FirebaseFirestore):TaskRepository{
        return TaskRepository(taskDAO = taskDAO,firestore)
    }

    @Singleton
    @Provides
    fun provideFirestoreInstance():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

}