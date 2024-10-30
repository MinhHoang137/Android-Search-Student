package com.example.searchstudent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import Student
class StudentAdapter(private var students: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val studentId: TextView = view.findViewById(R.id.studentId)
        val studentName: TextView = view.findViewById(R.id.studentName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.studentId.text = student.id
        holder.studentName.text = student.name
    }

    override fun getItemCount(): Int = students.size

    // Hàm này dùng để cập nhật danh sách sinh viên và thông báo thay đổi
    fun updateList(newList: List<Student>) {
        students = newList
        notifyDataSetChanged()
    }
}
