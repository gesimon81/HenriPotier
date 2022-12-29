package fr.android.paintersimon.presentation.Library

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.android.paintersimon.R
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.presentation.Detail.DetailActivity
import java.lang.reflect.Type


//
data class LibraryState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false
)

class LibraryActivity : AppCompatActivity() {

    private val viewModel by viewModels<LibraryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        //TODO: récupérer l'orientation et inflate le bon layout (fragments)
        // https://github.com/codepath/android_guides/wiki/Creating-and-Using-Fragments

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // This will pass the ArrayList to our Adapter
        var adapter = LibraryAdapter(ArrayList<Book>())

        //TODO : et si il n'y a pas d'accès internet ?

        // This will trigger when a book is clicked
        val packageContext = this;
        adapter.setOnItemCLickLIstener(object : LibraryAdapter.onItemCLickListener {
            override fun onItemClick(position: Int) {
                println("test")
                val intent = Intent(packageContext, DetailActivity::class.java)
                intent.putExtra("book", viewModel.getState().value?.books?.get(position))
                startActivity(intent)
            }
        })

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter as RecyclerView.Adapter<*>

        //observe
        viewModel.getState().observe(this) { state ->
            adapter.setList(state.books)
            adapter.notifyDataSetChanged()
        }

        loadData();


        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            // Begin the transaction
            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            // Replace the contents of the container with the new fragment
            if (savedInstanceState == null)
            {
                ft.replace(fr.android.paintersimon.R.id.detail_fragment, DetailFragment())
            }
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            ft.commit()
        }


        println("LibraryActivity")

    }

    private fun loadData() {
        println("viewModel.loadData()")

        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        println("sharedPreferences: " + sharedPreferences)

        // creating a variable for gson.
        val gson = Gson()

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        val json = sharedPreferences.getString("books", null)

        // below line is to get the type of our array list.
        val type: Type = object : TypeToken<List<Book?>?>() {}.type

        // in below line we are getting data from gson
        // and saving it to our array list
        val books= gson.fromJson<Any>(json, type)

        // checking below if the array list is empty or not
        if (books == null) {
            // if the array list is empty
            // creating a new array list.
            viewModel.initState()
            saveData()
        }else{
            viewModel.setBooks(books as List<Book>)
        }
    }

    private fun saveData() {
        println("viewModel.saveData()")

        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)

        // creating a variable for editor to
        // store data in shared preferences.
        val editor = sharedPreferences.edit()

        // creating a new variable for gson.
        val gson = Gson()

        // getting data from gson and storing it in a string.
        val json = gson.toJson(viewModel.getState().value?.books)

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("books", json)

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply()
    }
}
