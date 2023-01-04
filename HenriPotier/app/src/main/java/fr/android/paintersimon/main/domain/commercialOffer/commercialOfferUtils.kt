package fr.android.paintersimon.main.domain.commercialOffer

import java.util.LinkedList

class commercialOfferUtils {
    companion object {
        fun getBest(montant:Double, offers: List<Offer>) : Offer? {
            var bestOffer: Offer? = null
            var bestReduction: Double = 0.0

            offers.forEach { e->
                run {
                    val currentReduction = e!!.calculate(montant)
                    if (bestOffer == null || currentReduction < bestReduction) {
                        bestOffer = e
                    }
                    bestReduction = currentReduction
                }
            }
            return bestOffer
        }
    }
}