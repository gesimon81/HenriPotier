package fr.android.paintersimon.test

import fr.android.paintersimon.main.domain.commercialOffer.*
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.LinkedList

class PercentageOfferTest {

        //TODO tests

        @Test
        fun test() {
            //GIVEN
            val montantAchat:Double = 100.0
            val percentageOffer: PercentageOffer = PercentageOffer(15.0)
            val expectedResult:Double = 85.0

            //WHEN
            val result = percentageOffer.calculate(montantAchat)

            //THEN
            assertEquals(result,expectedResult)
        }
}