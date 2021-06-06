package com.renoirtan.todolist_android

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

/**
 * This adapter adds functionality to the recycler view #todo-items in activity_main.xml
 */
class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
// RecyclerView.Adapter<TodoViewHolder>:
// This adapter is responsible for todo views
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        // LayoutInflater: Get a reference to a view
        // Also f*ck you JetBrains for underlining British spelling
        return TodoViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_todo,
                    parent,
                    false
                )
        )
    }

    /**
     * Add todo item to list and then update display
     */
    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun removeChecked() {
        todos.removeAll { todo -> todo.isChecked }
        notifyDataSetChanged()
    }

    /**
     * Strikethrough if checkbox ticked
     */
    private fun toggleStrikeThrough(todo_item_title: TextView, isChecked: Boolean) {
        if (isChecked) {
            // Ah yes, no bitwise assign operators. Thank you so much, Kotlin :)
            todo_item_title.paintFlags = todo_item_title.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            todo_item_title.paintFlags = todo_item_title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
        // or in other languages:
        // if true:  flags |= 000010000 // include that extra bit
        // if false: flags &= 111101111 // sike the extra bit but preserve everything else
    }

    /**
     * Bind the data from `todos` to view when visible
     *
     * @param holder: TodoViewHolder
     * @param position: Int - Which insufferable child
     */
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var currentTodo = todos[position]
        // Set the title of the text to that of `currentTodo`
        // Apply values to attributes of `itemView`
        holder.itemView.apply {
            todo_item_title.text = currentTodo.title
            todo_item_checkbox.isChecked = currentTodo.isChecked
            // Set checkbox value
            toggleStrikeThrough(todo_item_title, currentTodo.isChecked)
            // When checkbox pressed, invert isChecked and update display
            todo_item_checkbox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todo_item_title, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { }

}