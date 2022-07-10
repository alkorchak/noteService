package data

data class Comment(
    val cid: Int = 0,
    val ownerId: Int,
    val message: String,
    val deleted: Boolean = false
)