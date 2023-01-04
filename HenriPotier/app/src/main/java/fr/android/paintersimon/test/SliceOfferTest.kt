package fr.android.paintersimon.test

import fr.android.paintersimon.main.domain.commercialOffer.*
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.LinkedList

class SliceOfferTest {

        //TODO tests

        @Test
        fun test() {
            //GIVEN
            val montantAchat:Double = 100.0
            val sliceOffer: SliceOffer = SliceOffer(15.0,100.0)
            val expectedResult:Double = 85.0

            //WHEN
            val result = sliceOffer.calculate(montantAchat)

            //THEN
            assertEquals(result,expectedResult)
        }
}