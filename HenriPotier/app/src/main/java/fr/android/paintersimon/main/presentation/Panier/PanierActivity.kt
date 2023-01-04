package fr.android.paintersimon.presentation.Panier

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.android.paintersimon.R
import fr.android.paintersimon.data.MyRetrofit
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.domain.Offers
import fr.android.paintersimon.domain.SousPanier
import fr.android.paintersimon.presentation.Library.LibraryActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.LinkedList


data class PanierState(
    var panier: MutableList<SousPanier> = LinkedList<SousPanier>(),
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

        //TODO : désactiver les boutons si le panier est vide

        //bouton pour retourner sur la liste
        val showListBooksButton  = findViewById<ImageButton>(R.id.showListBooksButton)
        showListBooksButton.setOnClickListener {
            val intent = Intent(packageContext, LibraryActivity::class.java)
            startActivity(intent)
        }

        val clearPanierButton  = findViewById<Button>(R.id.buttonClear)
        clearPanierButton.setOnClickListener {
            val text = "Le panier a été vidé"
            clearPanier(text, adapter)
        }

        val buyPanierButton  = findViewById<Button>(R.id.buttonBuy)
        buyPanierButton.setOnClickListener {
            val text = "Votre commande a été enregistré et le panier a été vidé"
            clearPanier(text, adapter)
        }

        CoroutineScope(Dispatchers.Main).launch { withContext(Dispatchers.IO) {
            println(getBestOffer())
        } }

    }



    fun clearPanier(text: String, adapter: PanierAdapter) {
        MyRetrofit.clearPanier()
        viewModel.state.value?.panier = LinkedList<SousPanier>()

        //affichage toast

        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()

        adapter.notifyDataSetChanged()
    }

    suspend fun getBestOffer(): Offers? {
        // Use a different CoroutineScope, etc
        //TODO: construire la chaîne des isbn
        //TODO offers =
            return MyRetrofit.getHenriPotierService()?.getCommercialOffer("a460afed-e5e7-4e39-a39d-c885c05db861,a460afed-e5e7-4e39-a39d-c885c05db861")

        //TODO: appeler CommercialOfferUtils.getBest(offers)
        //TODO : set un textField
    }
}
