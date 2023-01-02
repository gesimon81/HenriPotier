package fr.android.paintersimon.presentation.Detail

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import fr.android.paintersimon.R
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.presentation.Library.LibraryActivity
import fr.android.paintersimon.presentation.Panier.PanierActivity


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
            //affichage toast
            val text = "Aucun détail disponible"
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

            intent = Intent(packageContext, LibraryActivity::class.java)
            startActivity(intent)
        }else{

            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            if (savedInstanceState == null)
            {
                println("DetailFragment()")
                var detailFragment = DetailFragment()
                val bundle = Bundle()
                bundle.putParcelable("book", book)
                detailFragment.setArguments(bundle)
                ft.replace(fr.android.paintersimon.R.id.detail_fragment, detailFragment)
            }
            ft.commit()
        }

            //bouton pour consulter le panier
            findViewById<ImageButton>(R.id.showPanierButton)?.setOnClickListener {
            println("DetailFragment bouton showPanierButton clické")
            val intent = Intent(applicationContext, PanierActivity::class.java)
            startActivity(intent)
            }

            //bouton pour retourner sur la liste
            findViewById<ImageButton>(R.id.showListBooksButton)?.setOnClickListener {
                val intent = Intent(applicationContext, LibraryActivity::class.java)
                startActivity(intent)
            }
    }
}
