package fr.android.paintersimon.presentation.Detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.android.paintersimon.R
import fr.android.paintersimon.data.MyRetrofit
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.presentation.Library.LibraryActivity
import fr.android.paintersimon.presentation.Panier.PanierActivity


class DetailFragment() : Fragment() {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.activity_detail_fragment, parent, false)
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        //peupler les composants du layout avec le détail du livre


        val book: Book? = arguments?.getParcelable("book")

        if (book != null) {
            setBook(book);
        }

        //bouton pour consulter le panier
        getView()?.findViewById<ImageButton>(R.id.showPanierButton)?.setOnClickListener {
            println("DetailFragment bouton showPanierButton clické")
            val intent = Intent(context, PanierActivity::class.java)
            startActivity(intent)
        }

        //bouton pour retourner sur la liste
        getView()?.findViewById<ImageButton>(R.id.showListBooksButton)?.setOnClickListener {
            val intent = Intent(this.context, LibraryActivity::class.java)
            startActivity(intent)
        }

        println("DetailFragment onViewCreated")
    }

    public fun setBook(book: Book) {
        getView()?.findViewById<TextView>(R.id.titleTextView)?.text = book.title
        getView()?.findViewById<TextView>(R.id.priceTextView)?.text = book.price
        val coverImageView = getView()?.findViewById<ImageView>(R.id.coverImageView)
        if(coverImageView != null){
            Picasso.get().load(book.cover).into(coverImageView)
        }
        getView()?.findViewById<TextView>(R.id.synopsisTextView)?.text = book.synopsis.joinToString("")

        var panier = MyRetrofit.getPanier()
        getView()?.findViewById<Button>(R.id.addPanierButton)?.setOnClickListener {
            if (book != null) {
                MyRetrofit.addSousPanier(book)

                //TODO : add toast "Livre ajouté au panier"
                //add toast "Livre ajouté au panier"
                /*val text = "Livre ajouté au panier"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration) //TODO comment transmettre l'applicationContext depuis DetailActivity.kt ?
                toast.show()*/
            }
            
            println(panier.size)
        }
    }
}