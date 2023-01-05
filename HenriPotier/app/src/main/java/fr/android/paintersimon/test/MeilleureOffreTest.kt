package fr.android.paintersimon.test

import fr.android.paintersimon.domain.Offer
import fr.android.paintersimon.main.domain.commercialOffer.*
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.LinkedList

class MeilleureOffreTest {
        @Test
        fun minusOffer_is_best() {
            //GIVEN
            val montantAchat:Double = 110.0
            var offers : LinkedList<Offer> = LinkedList<Offer>()
            val percentageOffer: Offer = Offer("percentage",1)
            val sliceOffer: Offer = Offer("slice",10,100)
            val minusOffer: Offer = Offer("minus",15)

            offers.add(percentageOffer)
            offers.add(minusOffer)
            offers.add(sliceOffer)

            //WHEN
            val bestOffer: Offer? = CommercialOfferUtils.getBestOffer(montantAchat,offers)

            //THEN
            assertEquals(minusOffer,bestOffer)
        }
}