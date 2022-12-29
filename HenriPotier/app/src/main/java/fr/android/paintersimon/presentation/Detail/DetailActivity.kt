package fr.android.paintersimon.presentation.Detail

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import fr.android.paintersimon.R
import fr.android.paintersimon.data.MyRetrofit
import fr.android.paintersimon.data.MyRetrofit.Companion.getPanier
import fr.android.paintersimon.data.MyRetrofit.Companion.getRetrofitInstance
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.presentation.Library.LibraryActivity
import fr.android.paintersimon.presentation.Panier.PanierActivity
import retrofit2.Retrofit


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        println("timber start DetailActivity")

        val packageContext = this

        var intent = getIntent();
        val book: Book? = intent.getParcelableExtra<Book>("book")
        //val book: Book? = null //keep for test book null
        if (book == null){
            //TODO : vérifier si opération inutile
            //affichage toast
            val text = "Aucun détail disponible"
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

            intent = Intent(packageContext, LibraryActivity::class.java)
            startActivity(intent)
        }else{
            //peupler les composants du layout avec le détail du livre

            val titleTextVIew = findViewById<TextView>(R.id.titleTextView)
            titleTextVIew.setText(book.title)

            val priceTextView = findViewById<TextView>(R.id.priceTextView)
            priceTextView.setText(book.price)

            val coverImageView = findViewById<ImageView>(R.id.coverImageView)
            Picasso.get().load(book.cover).into(coverImageView);

            val synospsisTextView = findViewById<TextView>(R.id.synopsisTextView)
            synospsisTextView.setText(book.synopsis.joinToString(""))



            var panier = getPanier()
            val addPanierButton  = findViewById<Button>(R.id.addPanierButton)
            addPanierButton.setOnClickListener {
                MyRetrofit.addSousPanier(book)

                println(panier.size)

                //add toast "Livre ajouté au panier"
                val text = "Livre ajouté au panier"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }


        }

        //bouton pour consulter le panier
        val showPanierButton  = findViewById<ImageButton>(R.id.showPanierButton)
        showPanierButton.setOnClickListener {
            val intent = Intent(packageContext, PanierActivity::class.java)
            startActivity(intent)
        }

        //bouton pour retourner sur la liste
        val showListBooksButton  = findViewById<ImageButton>(R.id.showListBooksButton)
        showListBooksButton.setOnClickListener {
            val intent = Intent(packageContext, LibraryActivity::class.java)
            startActivity(intent)
        }
    }
}
