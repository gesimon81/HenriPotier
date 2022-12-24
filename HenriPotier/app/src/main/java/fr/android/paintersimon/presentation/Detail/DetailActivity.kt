package fr.android.paintersimon.presentation.Detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.android.paintersimon.R


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        println("timber start DetailActivity")

        //TODO : récupérer le livre à détailler

        //TODO : récupérer le détail du livre sur l'API HenriPotier

        //TODO peupler les composants du layout avec le détail du livre
    }
}
