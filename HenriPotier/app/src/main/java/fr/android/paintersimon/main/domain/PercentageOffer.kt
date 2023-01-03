package fr.android.paintersimon.main.domain

class PercentageOffer() : Offer() {
    private var percentage: Int = 0

    constructor(i: Int) : this(){
        this.percentage = i
    }

    override fun calculate(montant:Int): Double {
        TODO("Not yet implemented")
    }
}
