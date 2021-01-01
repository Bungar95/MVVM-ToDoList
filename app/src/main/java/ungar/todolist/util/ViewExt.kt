package ungar.todolist.util

import androidx.appcompat.widget.SearchView

// would've been searchview.onquerytextlistener in tasksfragment,
// but that has 2 methods but we only care about 1 so we're doing
// this viewext (view extension) function.

//[look up more about inline and crossinline]
inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true // we don't care about this one
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }
    })
}
