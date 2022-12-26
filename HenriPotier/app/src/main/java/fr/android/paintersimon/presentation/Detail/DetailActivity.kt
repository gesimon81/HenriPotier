package fr.android.paintersimon.presentation.Detail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import fr.android.paintersimon.R
import fr.android.paintersimon.data.MyRetrofit.Companion.getPanier
import fr.android.paintersimon.presentation.Panier.PanierActivity


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        println("timber start DetailActivity")

        val intent = getIntent();
        val title = intent.getStringExtra("title").orEmpty()
        val cover = intent.getStringExtra("cover").orEmpty()
        val price = intent.getStringExtra("price").orEmpty()

        //peupler les composants du layout avec le détail du livre

        val titleTextVIew = findViewById<TextView>(R.id.titleTextView)
        titleTextVIew.setText(title)

        val priceTextView = findViewById<TextView>(R.id.priceTextView)
        priceTextView.setText(price)

        val coverImageView = findViewById<ImageView>(R.id.coverImageView)
        Picasso.get().load(cover).into(coverImageView);

        val addPanierButton = findViewById<Button>(R.id.addPanierButton)
        var panier = getPanier()
        panier.add(title)
        // TODO : fournir un feedback utilisateur avec un petit toast ?

        //TODO configurer le bouton consulter panier
        val packageContext = this
        val showPanierButton  = findViewById<Button>(R.id.showPanierButton)
        showPanierButton.setOnClickListener {
            val intent = Intent(packageContext, PanierActivity::class.java)
            startActivity(intent)
        }
    }
}
