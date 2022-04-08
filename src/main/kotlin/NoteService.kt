class NoteService {
    private var notes = mutableListOf<Note>()
    private var deletedNotes = mutableListOf<Note>()
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
                val tempListOfComments = (note.comments).toMutableList()
                tempListOfComments.add(comment)
                val updatedNote = note.copy(comments = tempListOfComments)
                notes[notes.indexOf(note)] = updatedNote
                return true
            }
        }
        println("Такой заметки не существует!")
        return false
    }

    fun deleteNote(id: Int): Boolean {
        for (note in notes) {
            if (id == note.id) {
                note.comments = emptyList()
                note.deleted = true
                deletedNotes.add(note)
                notes.remove(note)
                return true
            }
        }
        println("Такой заметки не существует!")
        return false
    }

    fun deleteComment(commentId: Int): Boolean {
        for (note in notes) {
            for (comment in note.comments) {
                if (comment.id == commentId) {
                    val tempListOfComments = note.comments.toMutableList()
                    tempListOfComments.remove(comment)
                    val tempListOfDeletedComments = note.deletedComments.toMutableList()
                    tempListOfDeletedComments.add(comment)
                    val updatedNote = note.copy(
                        comments = tempListOfComments,
                        deletedComments = tempListOfDeletedComments
                    )
                    notes[notes.indexOf(note)] = updatedNote
                    return true
                }
            }
        }
        println("Такого комментария не существует!")
        return false
    }

    fun editNote(noteId: Int, title: String, text: String): Boolean {
        for (note in deletedNotes) {
            if (note.id == noteId) {
                println("Данная заметка удалена!")
                return false
            }
        }
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

    fun editComment(commentId: Int, message: String): Boolean {

        for (note in notes) {
            for (comment in note.deletedComments) {
                if (comment.id == commentId) {
                    println("Данный комментарий удален!")
                    return false
                }
            }
        }
        for (note in notes) {
            val tempListOfComments = note.comments.toMutableList()
            for (comment in note.comments) {
                if (comment.id == commentId) {
                    tempListOfComments[note.comments.indexOf(comment)] = comment.copy(
                        text = message
                    )
                    notes[notes.indexOf(note)] = note.copy(comments = tempListOfComments)
                    return true
                }
            }
        }
        println("Такого комментария не существует!")
        return false
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

    fun getComments(noteId: Int): List<Comment> {
        for ((index, note) in notes.withIndex()) {
            if (note.id == noteId) {
                return notes[index].comments
            }
        }
        return emptyList()
    }

    fun restoreComment(commentId: Int): Boolean {
        for (note in notes) {
            for (comment in note.deletedComments) {
                if (comment.id == commentId) {
                    val tempListOfComments = note.comments.toMutableList()
                    tempListOfComments.add(comment)
                    val tempListOfDeletedComments = note.deletedComments.toMutableList()
                    tempListOfDeletedComments.remove(comment)
                    val updatedNote = note.copy(
                        comments = tempListOfComments,
                        deletedComments = tempListOfDeletedComments
                    )
                    notes[notes.indexOf(note)] = updatedNote
                    return true
                }
            }
        }
        return false
    }
}