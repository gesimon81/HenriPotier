package fr.android.paintersimon.presentation.Panier

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.android.paintersimon.R
import fr.android.paintersimon.data.MyRetrofit
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.domain.Offer
import fr.android.paintersimon.domain.Offers
import fr.android.paintersimon.domain.SousPanier
import fr.android.paintersimon.main.domain.commercialOffer.CommercialOfferUtils
import fr.android.paintersimon.presentation.Library.LibraryActivity
import kotlinx.coroutines.*
import org.w3c.dom.Text
import java.util.LinkedList


data class PanierState(
    var panier: MutableList<SousPanier> = LinkedList<SousPanier>(),
    val isLoading: Boolean,
    var bestOfferMsg: String
)

class PanierActivity : AppCompatActivity() {

    private val viewModel by viewModels<PanierViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panier)

        println("timber start PanierActivity") //todo a suppr ?

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        val textViewBestOffer = findViewById<TextView>(R.id.TextViewBestOffer)

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
            textViewBestOffer.setText(state.bestOfferMsg)

            println("data change")
            val buyPanierButton = findViewById<Button>(R.id.buttonBuy)
            val clearPanierButton = findViewById<Button>(R.id.buttonClear)
            val textViewBestOffer = findViewById<TextView>(R.id.TextViewBestOffer)

            if(viewModel.state.value?.panier?.size  == 0){
                clearPanierButton.visibility = View.INVISIBLE
                buyPanierButton.visibility = View.INVISIBLE
                textViewBestOffer.setText("le panier est vide")

            }else{
                clearPanierButton.visibility = View.VISIBLE
                clearPanierButton.setOnClickListener {
                    val text = "Le panier a été vidé"
                    clearPanier(text, adapter)
                }
                buyPanierButton.visibility = View.VISIBLE
                buyPanierButton.setOnClickListener {
                    val text = "Votre commande a été enregistré et le panier a été vidé"
                    clearPanier(text, adapter)
                }
                textViewBestOffer.setText( viewModel.state.value?.bestOfferMsg)
            }
        }

        viewModel.loadPanier();

        val packageContext = this
        //bouton pour retourner sur la liste
        val showListBooksButton = findViewById<ImageButton>(R.id.showListBooksButton)
        showListBooksButton.setOnClickListener {
            val intent = Intent(packageContext, LibraryActivity::class.java)
            startActivity(intent)
        }


    }

    fun clearPanier(text: String, adapter: PanierAdapter) {
        MyRetrofit.clearPanier()
        viewModel.state.value?.panier = LinkedList()
        viewModel.state.value?.bestOfferMsg = ""

        //affichage toast
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()

        adapter.notifyDataSetChanged()
        val intent = Intent(applicationContext, LibraryActivity::class.java)
        startActivity(intent)
    }
}
