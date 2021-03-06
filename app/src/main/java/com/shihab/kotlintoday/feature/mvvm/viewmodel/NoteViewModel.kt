package com.shihab.kotlintoday.feature.mvvm.viewmodel

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shihab.kotlintoday.feature.mvvm.model.Note
import com.shihab.kotlintoday.feature.mvvm.repository.NoteRepository
import com.shihab.kotlintoday.feature.mvvm.ui.AddNoteActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(val context: Context) : ViewModel() {

    var repository: NoteRepository = NoteRepository(context)
    val note = Note()
    private var notes = MutableLiveData<List<Note>>()
    val message = MutableLiveData<String>()
    var isLoading = ObservableBoolean()

    init {
        getAllNotes()
    }

    fun saveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (checkValidation(note)) {
                    repository.insert(note)
                    message.postValue("Successfully Inserted")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getAllNotes() {
        isLoading.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            var mutableLiveData = mutableListOf<Note>()
            mutableLiveData.addAll(repository.getAllNotes())
            notes.postValue(mutableLiveData)
        }
    }

    fun getNotes(): MutableLiveData<List<Note>> = notes

    fun openAddNoteActivity() {
        context.startActivity(Intent(context, AddNoteActivity::class.java))
    }

    private fun checkValidation(note: Note): Boolean {

        var value = true

        if (TextUtils.isEmpty(note.title)) {
            message.postValue("Title is empty...")
            value = false
            return value
        }

        if (TextUtils.isEmpty(note.description)) {
            message.postValue("Description is empty...")
            value = false
            return value
        }

        if (TextUtils.isEmpty(note.priority)) {
            message.postValue("priority is empty...")
            value = false
            return value
        }

        return value
    }

    fun update(note: Note) {
        repository.update(note)

    }

    fun delete(note: Note) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }
}