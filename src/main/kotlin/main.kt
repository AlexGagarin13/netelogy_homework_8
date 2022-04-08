fun main() {
    val service = NoteService()
    val note1 = Note(1, 1, "New", "Первая", 10, emptyList(), emptyList(), 0, "###")
    service.addNote(note1)
    val note2 = Note(1, 1, "2", "Вторая", 15, emptyList(), emptyList(), 0, "###")
    service.addNote(note2)
    val note3 = Note(1, 1, "3", "Третья", 12, emptyList(), emptyList(), 0, "###")
    service.addNote(note3)
    val note4 = Note(1, 1, "4", "Четвертая", 30, emptyList(), emptyList(), 0, "###")
    service.addNote(note4)

    val comment1 = Comment(1, 1, 25, "Comment1")
    val comment2 = Comment(2, 2, 25, "Comment2")
    val comment3 = Comment(3, 3, 25, "Comment3")
    val comment4 = Comment(4, 1, 25, "Comment4")
    service.createComment(comment1)

//    service.createComment(comment1)
//    service.editComment(1, "###")

service.getNotes()

}