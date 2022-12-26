package fr.android.paintersimon.presentation.Library

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.android.paintersimon.R
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.presentation.Detail.DetailActivity


//
data class LibraryState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean
)

class LibraryActivity : AppCompatActivity() {

    private val viewModel by viewModels<LibraryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // This will pass the ArrayList to our Adapter
        var adapter = LibraryAdapter(ArrayList<Book>())

        //TODO : et si il n'y a pas d'accès internet ?

        // This will trigger when a book is clicked
        val packageContext = this;
        adapter.setOnItemCLickLIstener(object : LibraryAdapter.onItemCLickListener {
            override fun onItemClick(position: Int) {
                println("test")
                val intent = Intent(packageContext, DetailActivity::class.java)
                intent.putExtra("book", viewModel.state.value?.books?.get(position))
                startActivity(intent)
            }
        })

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter as RecyclerView.Adapter<*>

        //observe
        viewModel.state.observe(this) { state ->
            adapter.setList(state.books)
            adapter.notifyDataSetChanged()
        }

        viewModel.loadBooks();


    }
}
