package fr.android.paintersimon.presentation.Panier

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.android.paintersimon.R
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.domain.SousPanier
import fr.android.paintersimon.presentation.Library.LibraryActivity
import java.util.LinkedList


data class PanierState(
    val panier: MutableList<SousPanier> = LinkedList<SousPanier>(),
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
        var adapter = PanierAdapter(LinkedList<SousPanier>())

        //TODO : et si il n'y a pas d'accès internet ?

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter as RecyclerView.Adapter<*>

        //observe
        viewModel.state.observe(this) { state ->
            adapter.setList(state.panier)
            adapter.notifyDataSetChanged()
        }

        viewModel.loadBooks();


        val packageContext = this

        //bouton pour retourner sur la liste
        val showListBooksButton  = findViewById<ImageButton>(R.id.showListBooksButton)
        showListBooksButton.setOnClickListener {
            val intent = Intent(packageContext, LibraryActivity::class.java)
            startActivity(intent)
        }

        //TODO bouton pour vider le panier
        val clearPanierButton  = findViewById<Button>(R.id.buttonBuy)
        clearPanierButton.setOnClickListener {
            //viewModel.state.
        }
    }
}
