package ungar.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

//Data Access Object
//Interface for various database method that we'll use
@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table WHERE name LIKE '%' || :searchQuery || '%' ORDER BY important DESC")
    fun getTasks(searchQuery: String): Flow<List<Task>>
    // flow is an asynchronious stream of data
    // flow vs livedata

    //suspend -> kotlin coroutine feature, switch
    //to a different thread; if we did database
    //changes on main thread (responsible for ui),
    //the code would wait for the change which
    //could mean lagging/freezing of the screen

    //OnConflictStrategy -> for what to do when
    //tasks have same id.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}