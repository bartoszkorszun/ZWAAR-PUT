package put.inf154030.zwaar.relations

import androidx.room.Embedded
import androidx.room.Relation
import put.inf154030.zwaar.entities.Gear
import put.inf154030.zwaar.entities.User

data class UserGear(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "gearId"
    )
    val gearList: List<Gear>
)
