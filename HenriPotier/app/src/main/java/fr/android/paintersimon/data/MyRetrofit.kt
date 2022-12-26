package fr.android.paintersimon.data

import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.domain.HenriPotierService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.LinkedList

class MyRetrofit {



    private fun MyRetrofit() {}

    companion object {
        private var instance: Retrofit? = null
        private var panier: MutableList<Book>  =  LinkedList()

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

        fun getPanier():MutableList<Book>{
            return panier
        }

        fun createHenriPotierService(): HenriPotierService? {
            return  getRetrofitInstance()?.create(HenriPotierService::class.java);
        }
    }


}