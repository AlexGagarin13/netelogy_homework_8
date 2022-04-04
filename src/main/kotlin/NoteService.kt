class NoteService {
    private var notes = ArrayList<Note>()
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

    fun deleteNote(id: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.id == id) {
                notes[index].deleted = true
                deletedNotes.add(notes[index])
                notes.remove(notes[index])
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