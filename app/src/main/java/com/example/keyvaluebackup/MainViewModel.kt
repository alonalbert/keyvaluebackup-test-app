package com.example.keyvaluebackup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.io.File
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {

  private val _fileStatus = MutableLiveData<String>()
  val fileStatus: LiveData<String> = _fileStatus

  private val fileName = "backup_file"
  private val context = application.applicationContext

  init {
    checkFileExists()
  }

  fun createFile() {
    val file = File(context.filesDir, fileName)
    if (!file.exists()) {
      try {
        file.createNewFile()
        _fileStatus.value = "File Status: File Created"
      } catch (e: IOException) {
        _fileStatus.value = "File Status: Error creating file"
      }
    } else {
      _fileStatus.value = "File Status: File Already Exists"
    }
  }

  private fun checkFileExists() {
    val file = File(context.filesDir, fileName)
    if (file.exists()) {
      _fileStatus.value = "File Status: File Exists"
    } else {
      _fileStatus.value = "File Status: File Does Not Exist"
    }
  }
}