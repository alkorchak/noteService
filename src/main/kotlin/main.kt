import services.NoteService

fun main() {
    val noteserv = NoteService
    noteserv.add("Заголовок заметки 1", "текст заметки 1")
    noteserv.add("Заголовок заметки 2", "текст заметки 2")
    noteserv.add("Заголовок заметки 3", "текст заметки 3")
    noteserv.delete(2)
    noteserv.edit(3, "Title changed 3", "text changed 3")
    noteserv.createComment(1, "первый комментарий к заметке 1")
    noteserv.createComment(1, "второй комментарий к заметке 1")
    noteserv.createComment(3, "первый комментарий к заметке 3")
    noteserv.createComment(3, "второй комментарий к заметке 3")
    noteserv.deleteComment(4)
    noteserv.restoreComment(4)
    noteserv.editComment(3, "changed comment 3")
    println(noteserv.get())
}