package fr.android.paintersimon.presentation.Library

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.android.paintersimon.data.MyRetrofit
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.domain.HenriPotierService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class LibraryViewModel : ViewModel() {

    private val state = MutableLiveData<LibraryState>()
    fun initState() {
        println("viewModel.initState")

        //create service
        val service: HenriPotierService? = MyRetrofit.getHenriPotierService()
        var books: List<Book>? = null
        //aync request to update state
        val job = viewModelScope.launch(context = Dispatchers.Main) {
             books = withContext(Dispatchers.IO) {
                service?.listBooks()
            }
            state.postValue(books?.let { LibraryState(it, false) })
        }
    }

    fun getState(): MutableLiveData<LibraryState> {
        return state
    }

    fun setBooks(books:List<Book>){
        println("viewModel.setBooks")
        val service: HenriPotierService? = MyRetrofit.getHenriPotierService()
        //aync request to update state
        viewModelScope.launch(context = Dispatchers.Main) {
            state.postValue(books?.let { LibraryState(it, false) })
        }
    }
}