data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    var comments: Int,
    val readComments: Int,
    val viewUrl: String,
    var deleted: Boolean = false
) {
}