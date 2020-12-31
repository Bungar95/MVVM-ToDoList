package ungar.todolist.di

import android.app.Application
import androidx.room.CoroutinesRoom
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ungar.todolist.data.TaskDatabase
import javax.inject.Qualifier
import javax.inject.Singleton

// @Module makes it a dagger module.
// Dagger is used for di (dependency injection).
// Hilt makes using Dagger easier.
// "Instruction" functions are used for that.

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton //We only need 1 instance of dao and database so we use singleton
    fun provideDatabase(
        app: Application,
        callback: TaskDatabase.Callback
    ) = Room.databaseBuilder(app, TaskDatabase::class.java, "task_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideTaskDao(db: TaskDatabase) = db.taskDao()

    //create a scope for coroutine
    //supervisor tells the coroutine to continue other child if a child fails
    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

}

// ? need to check this more
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope