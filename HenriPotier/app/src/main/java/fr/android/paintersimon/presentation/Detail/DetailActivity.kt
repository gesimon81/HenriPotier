package fr.android.paintersimon.presentation.Detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import fr.android.paintersimon.R
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.presentation.Library.LibraryActivity


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
    }
}
