package fr.android.paintersimon.presentation.Library

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import fr.android.paintersimon.R
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.presentation.Detail.DetailFragment
import fr.android.paintersimon.presentation.Panier.PanierActivity


//
data class LibraryState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false
)

class LibraryActivity : AppCompatActivity() {

    private lateinit var detailFragment: DetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        println("LibraryActivity.onCreate()")

        val orientation = resources.configuration.orientation
        println("orientation: "+orientation)

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val libraryFragment = LibraryFragment()
        ft.replace(fr.android.paintersimon.R.id.library_fragment, libraryFragment)
        detailFragment = DetailFragment()
        ft.replace(fr.android.paintersimon.R.id.detail_fragment, detailFragment)

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            ft.hide(detailFragment)
        }
        ft.commit()

        //bouton pour consulter le panier
        val packageContext = this.applicationContext;
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


        //TODO : sauvegarder le state pour ne pas le recharger
        // https://www.youtube.com/watch?v=yhwtcEnI2Bg


        println("LibraryActivity")

    }

    fun setDetailBook(book: Book?) {
        if (book != null && detailFragment != null) {
            detailFragment.setBook(book)
        }
    }
}
