package fr.android.paintersimon.presentation.Library

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.android.paintersimon.R
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.presentation.Detail.DetailActivity
import java.lang.reflect.Type


class LibraryFragment : Fragment() {

    private val viewModel by viewModels<LibraryViewModel>()

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.activity_library_fragment, parent, false)
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("LibraryFragment.onCreateView")
        // Defines the xml file for the fragment
        // getting the recyclerview by its id
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        if (recyclerview != null) {
            recyclerview.layoutManager = LinearLayoutManager(context)
        }

        // This will pass the ArrayList to our Adapter
        var adapter = LibraryAdapter(ArrayList<Book>())

        //TODO : et si il n'y a pas d'accès internet ?

        // This will trigger when a book is clicked
        val packageContext = this.context;
        adapter.setOnItemCLickLIstener(object : LibraryAdapter.onItemCLickListener {
            override fun onItemClick(position: Int) {
                println("test")
                val orientation = resources.configuration.orientation
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    (activity as LibraryActivity?)?.setDetailBook(viewModel.getState().value?.books?.get(position))

                }else{
                    val intent = Intent(packageContext, DetailActivity::class.java)
                    intent.putExtra("book", viewModel.getState().value?.books?.get(position))
                    startActivity(intent)

                }
            }
        })

        // Setting the Adapter with the recyclerview
        if (recyclerview != null) {
            recyclerview.adapter = adapter as RecyclerView.Adapter<*>
        }

        //observe
        viewModel.getState().observe(this) { state ->
            adapter.setList(state.books)
            adapter.notifyDataSetChanged()
            saveData()
        }

        loadData();
    }



    private fun loadData() {
        println("viewModel.loadData()")

        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        val sharedPreferences = activity?.getSharedPreferences("shared preferences",
            AppCompatActivity.MODE_PRIVATE
        )
        println("sharedPreferences: $sharedPreferences")

        // creating a variable for gson.
        val gson = Gson()

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        val json = sharedPreferences?.getString("books", null)

        // below line is to get the type of our array list.
        val type: Type = object : TypeToken<List<Book?>?>() {}.type

        // in below line we are getting data from gson
        // and saving it to our array list
        var books= gson.fromJson<Any>(json, type)
        println("books: $books")

        // checking below if the array list is empty or not
        if (books == null) {
            // if the array list is empty
            // creating a new array list.
            books = viewModel.initState()
        }else{
            viewModel.setBooks(books as List<Book>)
        }
    }

    private fun saveData() {
        println("viewModel.saveData()")

        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        val sharedPreferences = getActivity()?.getSharedPreferences("shared preferences",
            AppCompatActivity.MODE_PRIVATE
        )

        // creating a variable for editor to
        // store data in shared preferences.
        val editor = sharedPreferences?.edit()

        // creating a new variable for gson.
        val gson = Gson()

        // getting data from gson and storing it in a string.
        val json = gson.toJson(viewModel.getState().value?.books)

        // below line is to save data in shared
        // prefs in the form of string.
        editor?.putString("books", json)

        // below line is to apply changes
        // and save data in shared prefs.
        editor?.apply()
    }
}