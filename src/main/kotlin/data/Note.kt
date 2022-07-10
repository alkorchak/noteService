package data

data class Note(
    val nid: Int = 0,
    val title: String,
    val text: String,
    val deleted: Boolean? = false,
)