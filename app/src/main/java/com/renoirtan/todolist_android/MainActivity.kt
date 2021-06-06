package com.renoirtan.todolist_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())
        todo_items.adapter = todoAdapter
        todo_items.layoutManager = LinearLayoutManager(this)

        // Grab the todo item inside the textbox and then add it to the adapter
        // using `addTodo` which updates the display
        // then clear the textbox
        add_item_submit.setOnClickListener {
            val todoTitle = add_item_input_field.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle, false)
                todoAdapter.addTodo(todo)
                add_item_input_field.text.clear()
            } else {
                // ignore the user
            }
        }

        remove_items_button.setOnClickListener {
            todoAdapter.removeChecked()
        }
    }
}