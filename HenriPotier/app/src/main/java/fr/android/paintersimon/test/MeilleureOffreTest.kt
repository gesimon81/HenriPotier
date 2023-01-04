package fr.android.paintersimon.test

import fr.android.paintersimon.main.domain.commercialOffer.*
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.LinkedList

class MeilleureOffreTest {

        //TODO tests

        @Test
        fun minusOffer_is_best() {
            //GIVEN
            val montantAchat:Double = 110.0
            var offers : LinkedList<Offer> = LinkedList<Offer>()
            val percentageOffer: PercentageOffer = PercentageOffer(1.0)
            val sliceOffer: SliceOffer = SliceOffer(10.0,100.0)
            val minusOffer: MinusOffer = MinusOffer(15.0)
            offers.add(percentageOffer)
            offers.add(minusOffer)
            offers.add(sliceOffer)

            //WHEN
            val bestOffer: Offer? = commercialOfferUtils.getBest(montantAchat,offers)

            //THEN
            assertEquals(minusOffer,bestOffer)
        }
}