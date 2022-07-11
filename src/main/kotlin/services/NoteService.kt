package services

import data.Comment
import data.Note
import exceptions.*

object NoteService {
    private var counter = 0
    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<Comment>()

    fun add(title: String, text: String): Int {
        val newNote = Note(counter++, title, text)
        notes.add(newNote)
        return newNote.nid
    }

    fun get(): List<Note> {
        val notesPresent = mutableListOf<Note>()
        val notesIterator = notes.iterator()
        while (notesIterator.hasNext()) {
            notesPresent.add(notesIterator.next())
        }
        if (notesPresent.isEmpty()) {
            throw NoNotesFound("Заметок нет!")
            } else return notesPresent
    }

    fun edit(noteId: Int, title: String, text: String): Boolean {
        val note = getByID(noteId)
        val index = notes.indexOf(note)
        notes[index] = note.copy(title = title, text = text)
        return true
    }

    fun delete(noteId: Int): Boolean {
        val note = getByID(noteId)
        return notes.remove(note)
        //notes.removeIf { it.nid == noteId }
        //comments.removeIf { it.ownerId == noteId }

    }

    fun getByID(noteId: Int): Note {
        try {
            return notes.single { it.nid == noteId }
        } catch (e: NoSuchElementException) {
            throw NoteNotFoundException("Заметка не найдена")
        }

    }

    fun createComment(noteId: Int, message: String): Int {
        val note = getByID(noteId)
        val newComment = Comment(comments.lastIndex + 2, note.nid, message, false)
        comments.add(newComment)
        return newComment.cid
    }

    fun getComments(noteId: Int): List<Comment> {
        val commentsPresent = mutableListOf<Comment>()
        val commentIterator = comments.iterator()
        for (item in commentIterator) {
            if (item.ownerId == noteId && !item.deleted) {
                commentsPresent.add(item)
            }
        }
        return commentsPresent
    }

    fun editComment(commentId: Int, message: String): Boolean {
        val comment = getCommentByID(commentId)
        comments[commentId - 1] = comment.copy(message = message)
        return true
    }

    fun deleteComment(commentId: Int): Boolean {
        val comment = getCommentByID(commentId)
        comments[commentId - 1] = comment.copy(deleted = true)
        return true
    }

    fun getCommentByID(commentId: Int): Comment {
        try {
            val comment = comments[commentId - 1]
            if (comment.deleted) {
                throw CommentDeletedException("Заметка была удалена")
            } else return comment
        } catch (e: IndexOutOfBoundsException) {
            throw CommentNotFoundException("Комментарий не найден")
        }
    }

    fun getDeletedCommentByID(commentId: Int): Comment {
        try {
            val comment = comments[commentId - 1]
            if (!comment.deleted) {
                throw CommentNotDeletedException("Заметка не была удалена")
            } else return comment
        } catch (e: IndexOutOfBoundsException) {
            throw CommentNotFoundException("Комментарий не найден")
        }
    }

    fun restoreComment(commentId: Int): Boolean {
        val comment = getDeletedCommentByID(commentId)
        comments[commentId - 1] = comment.copy(deleted = false)
        return true
    }

    fun clear() {
        notes.clear()
        comments.clear()
    }

    fun reset() {
        counter = 0
    }
}