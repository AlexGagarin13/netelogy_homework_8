import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun addNote() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)

        val result = service.addNote(note1)

        assertNotEquals(result.id, 0)
    }

    @Test
    fun createCommentSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val comment1 = Comment(1, 1, 25, "Comment1")

        service.addNote(note1)
        val result = service.createComment(comment1)

        assertTrue(result)
    }

    @Test
    fun createCommentNotSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val comment1 = Comment(1, 5, 25, "Comment1")

        service.addNote(note1)
        val result = service.createComment(comment1)

        assertFalse(result)
    }

    @Test
    fun deleteNoteSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)

        service.addNote(note1)
        val result = service.deleteNote(1)

        assertTrue(result)

    }

    @Test
    fun deleteNoteNotSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)

        service.addNote(note1)
        val result = service.deleteNote(5)

        assertFalse(result)

    }

    @Test
    fun deleteCommentSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val comment1 = Comment(1, 1, 25, "Comment1")

        service.addNote(note1)
        service.createComment(comment1)
        val result = service.deleteComment(1)

        assertTrue(result)
    }

    @Test
    fun deleteCommentNotSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val comment1 = Comment(1, 1, 25, "Comment1")

        service.addNote(note1)
        service.createComment(comment1)
        val result = service.deleteComment(2)

        assertFalse(result)
    }

    @Test
    fun editNoteSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)

        service.addNote(note1)
        val result = service.editNote(1, "Edited tittle", "Edited Note")

        assertTrue(result)
    }

    @Test
    fun editNoteNotSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)

        service.addNote(note1)
        val result = service.editNote(2, "Edited tittle", "Edited Note")

        assertFalse(result)
    }

    @Test
    fun editNoteNotSuccessfullyBecauseDeleted() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)

        service.addNote(note1)
        service.deleteNote(1)
        val result = service.editNote(1, "Edited tittle", "Edited Note")

        assertFalse(result)
    }

    @Test
    fun editCommentSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val comment1 = Comment(1, 1, 25, "Comment1")

        service.addNote(note1)
        service.createComment(comment1)
        val result = service.editComment(1, "Edited comment")

        assertTrue(result)
    }

    @Test
    fun editCommentNotSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val comment1 = Comment(1, 1, 25, "Comment1")

        service.addNote(note1)
        service.createComment(comment1)
        val result = service.editComment(2, "Edited comment")

        assertFalse(result)
    }

    @Test
    fun editCommentNotSuccessfullyBecauseDeleted() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val comment1 = Comment(1, 1, 25, "Comment1")

        service.addNote(note1)
        service.createComment(comment1)
        service.deleteComment(1)
        val result = service.editComment(1, "Edited comment")

        assertFalse(result)
    }

    @Test
    fun getNotesDescending() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val note2 = Note(0, 1, "Second", "Вторая", 11, emptyList(), emptyList(), 0, "###", false)

        service.addNote(note1)
        service.addNote(note2)

        val result = service.getNotes()

        assertEquals(
            listOf(
                Note(
                    id = 2,
                    ownerId = 1,
                    title = "Second",
                    text = "Вторая",
                    date = 11,
                    comments = emptyList(),
                    deletedComments = emptyList(),
                    readComments = 0,
                    viewUrl = "###",
                    deleted = false
                ),
                Note(
                    id = 1,
                    ownerId = 1,
                    title = "First",
                    text = "Первая",
                    date = 10,
                    comments = emptyList(),
                    deletedComments = emptyList(),
                    readComments = 0,
                    viewUrl = "###",
                    deleted = false
                )
            ),
            result
        )
    }

    @Test
    fun getNotesNotDescending() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val note2 = Note(0, 1, "Second", "Вторая", 11, emptyList(), emptyList(), 0, "###", false)

        service.addNote(note1)
        service.addNote(note2)

        val result = service.getNotes(sort = 1)

        assertEquals(
            listOf(
                Note(
                    id = 1,
                    ownerId = 1,
                    title = "First",
                    text = "Первая",
                    date = 10,
                    comments = emptyList(),
                    deletedComments = emptyList(),
                    readComments = 0,
                    viewUrl = "###",
                    deleted = false
                ),
                Note(
                    id = 2,
                    ownerId = 1,
                    title = "Second",
                    text = "Вторая",
                    date = 11,
                    comments = emptyList(),
                    deletedComments = emptyList(),
                    readComments = 0,
                    viewUrl = "###",
                    deleted = false
                )
            ),
            result
        )
    }


    @Test
    fun getByIdSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)

        service.addNote(note1)
        val result = service.getById(1)

        assertEquals(Note(1, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false), result)
    }

    @Test
    fun getByIdNotSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)

        service.addNote(note1)
        val result = service.getById(2)

        assertNotEquals(Note(1, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false), result)
    }


    @Test
    fun getCommentsSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val comment1 = Comment(1, 1, 25, "Comment1")

        service.addNote(note1)
        service.createComment(comment1)
        val result = service.getComments(1)

        assertEquals(listOf(Comment(1, 1, 25, "Comment1")), result)
    }

    @Test
    fun getCommentsNotSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val comment1 = Comment(1, 1, 25, "Comment1")

        service.addNote(note1)
        service.createComment(comment1)
        val result = service.getComments(2)

        assertNotEquals(listOf(Comment(1, 1, 25, "Comment1")), result)
    }

    @Test
    fun restoreCommentSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val comment1 = Comment(1, 1, 25, "Comment1")

        service.addNote(note1)
        service.createComment(comment1)
        service.deleteComment(1)
        val result = service.restoreComment(1)

        assertTrue(result)
    }

    @Test
    fun restoreCommentNotSuccessfully() {
        val service = NoteService()
        val note1 = Note(0, 1, "First", "Первая", 10, emptyList(), emptyList(), 0, "###", false)
        val comment1 = Comment(1, 1, 25, "Comment1")

        service.addNote(note1)
        service.createComment(comment1)
        service.deleteComment(1)
        val result = service.restoreComment(2)

        assertFalse(result)
    }
}