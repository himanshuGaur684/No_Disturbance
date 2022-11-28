package gaur.himanshu.wear_app.presentation.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gaur.himanshu.wear_app.presentation.repository.WearTaskRepository

@InstallIn(SingletonComponent::class)
@Module
object WearAppModule {

    @Provides
    fun provideWearTaskRepository(fireStore: FirebaseFirestore): WearTaskRepository {
        return WearTaskRepository(fireStore)
    }

    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}