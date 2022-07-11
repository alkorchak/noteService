import services.NoteService

fun main() {
    val noteserv = NoteService

    noteserv.add("Заголовок заметки 1", "текст заметки 1")
    noteserv.add("Заголовок заметки 2", "текст заметки 2")
    noteserv.add("Заголовок заметки 3", "текст заметки 3")
    noteserv.delete(2)
    noteserv.add("Заголовок заметки 4", "текст заметки 4")
    println(noteserv.get())

    noteserv.edit(3, "Title changed 3", "text changed 3")
    println(noteserv.get())
    noteserv.createComment(1, "первый комментарий к заметке 1")
    noteserv.createComment(3, "первый комментарий к заметке 3")
    println(noteserv.getComments(1))
    println(noteserv.getComments(3))
    noteserv.delete(3)
    println(noteserv.get())
    println(noteserv.getComments(3))
    noteserv.editComment(1, "changed comment 3")
    println(noteserv.getComments(1))
}