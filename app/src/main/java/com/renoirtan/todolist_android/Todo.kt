package com.renoirtan.todolist_android

data class Todo(
    val title: String,
    var isChecked: Boolean
)

// Inquiry for JetBrains
// Why is the default behaviour when backspacing with only tabs/spaces in front of the cursor
// to immediately return to the previous line?
// Makes formatting much more difficult pls fix
