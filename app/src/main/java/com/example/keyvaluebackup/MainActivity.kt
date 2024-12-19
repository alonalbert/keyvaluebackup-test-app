package com.example.keyvaluebackup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

  private lateinit var viewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    val fileStatusTextView = findViewById<TextView>(R.id.fileStatusTextView)
    val createFileButton = findViewById<Button>(R.id.createFileButton)

    viewModel.fileStatus.observe(this) { status ->
      fileStatusTextView.text = status
    }

    createFileButton.setOnClickListener {
      viewModel.createFile()
    }
  }
}