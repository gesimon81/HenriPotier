package fr.android.paintersimon.presentation.Detail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import fr.android.paintersimon.R
import fr.android.paintersimon.data.MyRetrofit
import fr.android.paintersimon.data.MyRetrofit.Companion.getPanier
import fr.android.paintersimon.data.MyRetrofit.Companion.getRetrofitInstance
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.presentation.Panier.PanierActivity
import retrofit2.Retrofit


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        println("timber start DetailActivity")

        val intent = getIntent();
        val book: Book? = intent.getParcelableExtra<Book>("book")
        if (book == null){
            //TODO : handle null book
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

            val packageContext = this

            var panier = getPanier()
            val addPanierButton  = findViewById<Button>(R.id.addPanierButton)
            addPanierButton.setOnClickListener {
                MyRetrofit.addSousPanier(book)
                //TODO : add toast "Livre ajouté au panier"
                println(panier.size)
            }

            val showPanierButton  = findViewById<Button>(R.id.showPanierButton)
            showPanierButton.setOnClickListener {
                val intent = Intent(packageContext, PanierActivity::class.java)
                startActivity(intent)
            }
        }


    }
}
