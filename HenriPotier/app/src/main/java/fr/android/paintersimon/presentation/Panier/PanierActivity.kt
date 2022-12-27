package fr.android.paintersimon.presentation.Panier

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.android.paintersimon.R
import fr.android.paintersimon.domain.Book


data class PanierState(
    val panier: MutableMap<Book, Int> = HashMap<Book, Int>(),
    val isLoading: Boolean
)

class PanierActivity : AppCompatActivity() {

    private val viewModel by viewModels<PanierViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panier)

        println("timber start PanierActivity")


        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // This will pass the ArrayList to our Adapter
        var adapter = PanierAdapter(HashMap<Book, Int>())

        //TODO : et si il n'y a pas d'accès internet ?

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter as RecyclerView.Adapter<*>

        //observe
        viewModel.state.observe(this) { state ->
            adapter.setList(state.panier)
            adapter.notifyDataSetChanged()
        }

        viewModel.loadBooks();

    }
}
