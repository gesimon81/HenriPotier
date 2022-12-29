package fr.android.paintersimon.presentation.Detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import fr.android.paintersimon.R
import fr.android.paintersimon.domain.Book


class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        println("timber start DetailActivity")

        val packageContext = this

        val intent = getIntent();
        val book: Book? = intent.getParcelableExtra<Book>("book")
        if (book == null){
            //TODO : handle null book
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
