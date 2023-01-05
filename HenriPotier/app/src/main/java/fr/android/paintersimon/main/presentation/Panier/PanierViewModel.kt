package fr.android.paintersimon.presentation.Panier

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.android.paintersimon.data.MyRetrofit
import fr.android.paintersimon.domain.HenriPotierService
import fr.android.paintersimon.domain.Offer
import fr.android.paintersimon.domain.SousPanier
import fr.android.paintersimon.main.domain.commercialOffer.CommercialOfferUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.LinkedList
import kotlin.math.round
import kotlin.math.roundToLong


class PanierViewModel : ViewModel() {

    val state = MutableLiveData<PanierState>()
    fun loadPanier() {
        //state at application start
        state.postValue(PanierState(LinkedList<SousPanier>(), true,""))

        //aync request to update state
        viewModelScope.launch(context = Dispatchers.Main) {
            var bestOfferMsg: String = ""
            val books = MyRetrofit.getPanier()
            if (books.size > 0) {
                val montantAchat: Double = CommercialOfferUtils.getMontantAchat().toDouble()

                val bestOffer: Offer? = CommercialOfferUtils.getBestOffer()
                if (bestOffer != null) {
                    val montantApresReduction: Double =
                        CommercialOfferUtils.evaluate(bestOffer, montantAchat)
                    val df = DecimalFormat("#.##")
                    df.roundingMode = RoundingMode.HALF_EVEN
                    val roundedReduction = df.format(montantAchat - montantApresReduction)
                    bestOfferMsg += "Montant achat avant réduction: $montantAchat euros\n"
                    bestOfferMsg += "Réduction: $roundedReduction euros\n"
                    bestOfferMsg += "Montant achat après réduction: $montantApresReduction euros"
                }
            }
            state.postValue(books?.let { PanierState(it, false,bestOfferMsg) })
        }
    }

}