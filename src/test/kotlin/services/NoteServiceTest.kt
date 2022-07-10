package services
import data.*
import exceptions.*
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import kotlin.math.exp

class NoteServiceTest {

    val noteserv = NoteService

    @After
    fun clear() {
        noteserv.clear()
    }


    @Test
    fun add() {

        val result = noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        assertEquals(1,result)
    }

    @Test
    fun getOk() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        val note = Note(1,"Заголовок заметки 1" , "текст заметки 1", false)
        val expected = mutableListOf(note)
        val result = noteserv.get()
        assertEquals(expected,result)
    }

    @Test
    fun editOk() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        val expected = noteserv.edit(1, "Title changed 1" , "text changed 1")
        assertTrue(expected)
    }

    @Test
    fun deleteOk() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        val expected = noteserv.delete(1)
        assertTrue(expected)
    }

    @Test
    fun getByIDOk() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        val result = noteserv.getByID(1)
        val expected = Note(1,"Заголовок заметки 1" , "текст заметки 1", false)
        assertEquals(expected,result)
    }

    @Test
    fun createCommentOk() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        val result = noteserv.createComment(1,"comment")
        assertEquals(1,result)
    }

    @Test
    fun getCommentsOk() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        noteserv.createComment(1, "первый комментарий к заметке 1")
        val comment = Comment(1,1, "первый комментарий к заметке 1", false)
        val expected = mutableListOf(comment)
        val result = noteserv.getComments(1)
        assertEquals(expected,result)
    }

    @Test
    fun editCommentOk() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        noteserv.createComment(1, "первый комментарий к заметке 1")
        val expected = noteserv.editComment(1,"edited comment")
        assertTrue(expected)
    }

    @Test
    fun deleteCommentOk() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        noteserv.createComment(1, "первый комментарий к заметке 1")
        val expected = noteserv.deleteComment(1)
        assertTrue(expected)
    }

    @Test
    fun restoreCommentOk() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        noteserv.createComment(1, "первый комментарий к заметке 1")
        noteserv.deleteComment(1)
        val expected = noteserv.restoreComment(1)
        assertTrue(expected)
    }

    @Test(expected = NoteNotFoundException::class)
        fun getByIDTest1() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        noteserv.delete(2)
        }


    @Test(expected = NoteDeletedException::class)
        fun getByIDTest2() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        noteserv.delete(1)
        noteserv.delete(1)
        }

    @Test(expected = CommentNotFoundException::class)
        fun getCommentByID1() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        noteserv.createComment(1, "первый комментарий к заметке 1")
        noteserv.deleteComment(2)

        }


    @Test(expected = CommentDeletedException::class)
         fun getCommentByID2() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        noteserv.createComment(1, "первый комментарий к заметке 1")
        noteserv.deleteComment(1)
        noteserv.deleteComment(1)
        }

    @Test(expected = CommentNotDeletedException::class)
        fun restoreDeletedException() {
        noteserv.add("Заголовок заметки 1" , "текст заметки 1")
        noteserv.createComment(1, "первый комментарий к заметке 1")
        noteserv.restoreComment(1)
        }



}