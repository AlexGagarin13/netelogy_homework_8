data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    var comments: List<Comment>,
    var deletedComments: List<Comment>,
    val readComments: Int,
    val viewUrl: String,
    var deleted: Boolean = false
) {
}