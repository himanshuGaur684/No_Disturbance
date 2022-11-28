package gaur.himanshu.nodisturbance.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val activityName:String="",
    val relatedActivity:String="",
    @PrimaryKey(autoGenerate = false)
    var id:String=""
):java.io.Serializable
