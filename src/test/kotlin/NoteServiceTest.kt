import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun addNote() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###")

        val result = service.addNote(note1)

        assertNotEquals(result.id, 0)
    }

    @Test
    fun createComment() {
    }

    @Test
    fun deleteNote() {
    }

    @Test
    fun deleteComment() {
    }

    @Test
    fun editNote() {
    }

    @Test
    fun editComment() {
    }

    @Test
    fun getNotes() {
    }

    @Test
    fun getById() {
    }

    @Test
    fun getComments() {
    }

    @Test
    fun restoreComment() {
    }
}