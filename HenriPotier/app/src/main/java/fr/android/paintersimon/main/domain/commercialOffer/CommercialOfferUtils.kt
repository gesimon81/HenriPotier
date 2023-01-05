package fr.android.paintersimon.main.domain.commercialOffer

import fr.android.paintersimon.data.MyRetrofit
import fr.android.paintersimon.domain.Offer
import fr.android.paintersimon.domain.Offers
import java.util.*

class CommercialOfferUtils {
    companion object {

        suspend fun getBestOffer(): Offer? {
            val offers: List<Offer>? = getCommercialOffers()?.offers
            val montantAchat:Double = getMontantAchat().toDouble()
            return offers?.let { getBestOffer(montantAchat, it) }
        }

        fun getBestOffer(montantAchat: Double, offers: kotlin.collections.List<Offer>): Offer? {
            var bestOffer: Offer? = null
            var bestReduction: Double = 0.0

            offers.forEach { e ->
                run {
                    val currentReduction = evaluate(e, montantAchat)
                    if (bestOffer == null || currentReduction < bestReduction) {
                        bestOffer = e
                    }
                    bestReduction = currentReduction
                }
            }
            return bestOffer
        }

        fun getMontantAchat():Int{
            var result : Int = 0
            MyRetrofit.getPanier().forEach { e ->
                run {
                    var i = e.quantity
                    while (i > 0) {
                        result += e.book.price.toInt()
                        i--
                    }
                }
            }
            return result
        }

        // Calcule le montant après réduction
        fun evaluate(offer: Offer, montant: Double): Double{
            if(offer.type.equals("percentage")){
                return montant * (100-offer.value)/100
            }else if(offer.type.equals("minus")){
                return montant - offer.value
            }else if(offer.type.equals("slice")){
                val numberOfSlices: Int = (montant/ offer.sliceValue!!).toInt()
                return montant-offer.value*numberOfSlices
            }else{
                throw Exception("Unrecognized offer")
            }
        }

        suspend fun getCommercialOffers():Offers?{
            var isbns: String = ""
            MyRetrofit.getPanier().forEach { e ->
                run {
                    var i = e.quantity
                    while (i > 0) {
                        isbns += e.book.isbn +","
                        i--
                    }
                }
            }
            return MyRetrofit.getHenriPotierService()
                ?.getCommercialOffer(isbns)
        }
    }
}