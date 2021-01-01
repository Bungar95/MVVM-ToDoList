package ungar.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ungar.todolist.ui.tasks.SortOrder

//Data Access Object
//Interface for various database method that we'll use
@Dao
interface TaskDao {

    fun getTasks(query: String, sortOrder: SortOrder, hideCompleted: Boolean): Flow<List<Task>> =
        when(sortOrder){
            SortOrder.BY_DATE -> getTasksSortedByDateCreated(query,hideCompleted)
            SortOrder.BY_NAME -> getTasksSortedByName(query, hideCompleted)
        }

    @Query("SELECT * FROM task_table WHERE (completed != :hideCompleted OR completed = 0) AND name LIKE '%' || :searchQuery || '%' ORDER BY important DESC, name")
    fun getTasksSortedByName(searchQuery: String, hideCompleted: Boolean): Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE (completed != :hideCompleted OR completed = 0) AND name LIKE '%' || :searchQuery || '%' ORDER BY important DESC, created")
    fun getTasksSortedByDateCreated(searchQuery: String, hideCompleted: Boolean): Flow<List<Task>>


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