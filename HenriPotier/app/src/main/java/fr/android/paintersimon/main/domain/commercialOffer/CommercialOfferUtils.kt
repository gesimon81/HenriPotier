package fr.android.paintersimon.main.domain.commercialOffer

import fr.android.paintersimon.domain.Offer

class CommercialOfferUtils {
    companion object {
        fun getBest(montant:Double, offers: List<Offer>) : Offer? {
            var bestOffer: Offer? = null
            var bestReduction: Double = 0.0

            offers.forEach { e->
                run {
                    val currentReduction = evaluate(e,montant)
                    if (bestOffer == null || currentReduction < bestReduction) {
                        bestOffer = e
                    }
                    bestReduction = currentReduction
                }
            }
            return bestOffer
        }

        fun evaluate(offer: Offer, montant:Double): Double{
            if(offer == null){
                throw Exception("Offer is null")
            }
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
    }
}