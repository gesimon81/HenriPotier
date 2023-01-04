package fr.android.paintersimon.test

import fr.android.paintersimon.main.domain.commercialOffer.*
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.LinkedList

class MinusOfferTest {

        //TODO tests

        @Test
        fun test() {
            //GIVEN
            val montantAchat:Double = 89.0
            val minusOffer: MinusOffer = MinusOffer(20.0)
            val expectedResult:Double = 69.0

            //WHEN
            val result = minusOffer.calculate(montantAchat)

            //THEN
            assertEquals(result,expectedResult)
        }
}