package fr.android.paintersimon.presentation.Panier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.android.paintersimon.R
import fr.android.paintersimon.data.MyRetrofit.Companion.getPanier


class PanierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panier)

        println("timber start PanierActivity")

        //TODO peupler composants pour afficher panier
        val panier = getPanier()
    }
}
