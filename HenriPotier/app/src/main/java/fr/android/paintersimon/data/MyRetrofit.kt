package fr.android.paintersimon.data

import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.domain.HenriPotierService
import fr.android.paintersimon.domain.SousPanier
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.LinkedList

class MyRetrofit {



    private fun MyRetrofit() {}

    companion object {
        private var instance: Retrofit? = null
        private var panier: LinkedList<SousPanier>  =  LinkedList()

        fun getRetrofitInstance(): Retrofit? {
            if (instance == null) {
                synchronized(MyRetrofit::class.java) {
                    if (instance == null) {
                        instance = Retrofit.Builder()
                            .baseUrl("https://henri-potier.techx.fr")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                    }
                }
            }
            return instance
        }

        fun getPanier():LinkedList<SousPanier>{
            return panier
        }

        fun addSousPanier(book: Book){
            var found : Boolean = false
            panier.forEach { sp ->
                run {
                    if (sp.book == book) {
                        sp.quantity++
                        found = true
                    }
                }
            }
            if(!found){
                panier.add(SousPanier(book,1))
            }
        }

        fun createHenriPotierService(): HenriPotierService? {
            println("createHenriPotierService")
            return  getRetrofitInstance()?.create(HenriPotierService::class.java);
        }
    }
}