package fr.android.paintersimon.test

import fr.android.paintersimon.main.domain.PercentageOffer
import junit.framework.Assert.assertEquals
import org.junit.Test

class MeilleureOffreTest {

        //TODO tests

        @Test
        fun percentageOffer_is_the_best() {
            //GIVEN
            //montantAchat
            //percentageOffer(value)
            //minusOffer(value)
            //sliceOffer(sliceValue, value)
            val montantAchat:Int = 110
            val percentageOffer: PercentageOffer = PercentageOffer(10)

            //WHEN
            //appel à l'algo de calcul de la meilleure offre
            //retourne la meilleure offre

            //THEN
            // expected = meilleure offre
            assertEquals(true, true)
        }
}