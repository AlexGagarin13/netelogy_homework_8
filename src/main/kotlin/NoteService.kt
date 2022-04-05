class NoteService {
    private var notes = mutableListOf<Note>()
    private var notesWIthComments = mutableMapOf<Int, MutableList<Comment>>()
    private var deletedNotes = ArrayList<Note>()
    private var NoteId = 0


    private fun generateId(): Int {
        NoteId += 1
        return NoteId
    }

    fun addNote(note: Note): Note {
        notes.add(note.copy(id = generateId()))
        return notes.last()
    }

    fun createComment(comment: Comment): Boolean {
        for (note in notes) {
            if (comment.noteId == note.id) {
                notesWIthComments.getOrPut(note.id) { mutableListOf() }.add(comment)
                note.comments += 1
                return true
            }
        }
        println("Такой заметки не существует!")
        return false
    }

    fun deleteNote(id: Int): Boolean {
        for (note in notes) {
            if (id == note.id) {
                note.deleted = true
                deletedNotes.add(note)
                notes.remove(note)
                notesWIthComments.remove(note.id)
                return true
            }
        }
        println("Такой заметки не существует!")
        return false
    }

    fun editNote(noteId: Int, title: String, text: String): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.id == noteId) {
                notes[index] = note.copy(
                    title = title,
                    text = text
                )
                return true
            }
        }
        println("Такой заметки не существует!")
        return false
    }

    fun editComment(commentId: Int, noteId: Int, message: String) {
//  TODO      val noteWithCommentToEdit = notesWIthComments.filterKeys { it == noteId }
//        noteWithCommentToEdit.
    }
}

fun getNotes(
    offset: Int = 0,
    count: Int = 20,
    sort: Int = 0
): List<Note> {
    val sortedListToShow: List<Note> = if (sort == 0) {
        notes.sortedByDescending { it.date }
    } else {
        notes.sortedBy { it.date }
    }
    return sortedListToShow
}

fun getById(id: Int): Note? {
    for ((index, note) in notes.withIndex()) {
        if (note.id == id) {
            return notes[index]
        }
    }
    println("Такой заметки не существует!")
    return null
}
}