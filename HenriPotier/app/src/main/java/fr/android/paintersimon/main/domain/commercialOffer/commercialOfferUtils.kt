package fr.android.paintersimon.main.domain.commercialOffer

import java.util.LinkedList

class commercialOfferUtils {
    companion object {
        fun getBest(montant:Double, offers: List<Offer>) : Offer? {
            var bestOffer: Offer? = null
            var bestReduction: Double = 0.0

            offers.forEach { e->
                run {
                    if (bestOffer == null || bestOffer!!.calculate(montant) < bestReduction) {
                        bestOffer = e
                    }
                }
            }
            return bestOffer
        }
    }
}