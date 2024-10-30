package com.example.searchstudent

import Student
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private val students = listOf(
        Student("20225719", "Dang Minh Hoang"),
        Student("20226369", "Nguyen Xuan Huy"),
        Student("20228386", "Bui Quoc Bao"),
        Student("20225001", "Tran Van An"),
        Student("20225002", "Le Thi Bich"),
        Student("20225003", "Nguyen Van Cuong"),
        Student("20225004", "Pham Minh Duc"),
        Student("20225005", "Do Quang Huy"),
        Student("20225006", "Nguyen Thi Lan"),
        Student("20225007", "Hoang Van Khanh"),
        Student("20225008", "Pham Van Minh"),
        Student("20225009", "Vu Thi Ngoc"),
        Student("20225010", "Nguyen Van Phuc"),
        Student("20225011", "Le Van Quan"),
        Student("20225012", "Phan Thi Quyen"),
        Student("20225013", "Do Thanh Son"),
        Student("20225014", "Vu Minh Tam"),
        Student("20225015", "Nguyen Thi Uyen"),
        Student("20225016", "Pham Van Vuong")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.StudentHolder)
        studentAdapter = StudentAdapter(students)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        val searchBox = findViewById<EditText>(R.id.SearchBox)
        searchBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                filterList(query)
            }
        })
    }

    private fun filterList(query: String) {
        val filteredList = when {
            query.length == 1 -> students
            query.length >= 2 && query.all { it.isDigit() } ->
                students.filter { it.id.contains(query) }
            query.length >= 2 ->
                students.filter { it.name.contains(query, ignoreCase = true) }
            else -> students
        }
        studentAdapter.updateList(filteredList)
    }
}