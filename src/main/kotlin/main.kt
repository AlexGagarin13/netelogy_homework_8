fun main() {
    val service = NoteService()
    val note1 = Note(1, 1, "New", "New", 10, 0, 0, "###")
    println(service.addNote(note1))
    val note2 = Note(1, 1, "2", "New", 15, 0, 0, "###")
    println(service.addNote(note2))
    val note3 = Note(1, 1, "3", "New", 12, 0, 0, "###")
    println(service.addNote(note3))
    val note4 = Note(1, 1, "4", "New", 30, 0, 0, "###")
    println(service.addNote(note4))

    service.editNote(2,"Edited", "##")


}