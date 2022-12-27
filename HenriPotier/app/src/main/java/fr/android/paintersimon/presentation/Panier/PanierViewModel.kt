package fr.android.paintersimon.presentation.Panier

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.android.paintersimon.data.MyRetrofit
import fr.android.paintersimon.domain.HenriPotierService
import fr.android.paintersimon.domain.SousPanier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.LinkedList


class PanierViewModel : ViewModel() {

    val state = MutableLiveData<PanierState>()
    fun loadBooks() {
        //state at application start
        state.postValue(PanierState(LinkedList<SousPanier>(), true))

        //aync request to update state
        viewModelScope.launch(context = Dispatchers.Main) {
            val books = MyRetrofit.getPanier()
            state.postValue(books?.let { PanierState(it, false) })
        }
    }
}