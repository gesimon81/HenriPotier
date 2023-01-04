package fr.android.paintersimon.main.domain.commercialOffer

class MinusOffer() : Offer() {
    private var value: Double = 0.0

    constructor(valuep: Double) : this(){
        this.value = valuep
    }

    override fun calculate(montant:Double): Double {
        return (montant - value)
    }
}
