package fr.android.paintersimon.main.domain.commercialOffer

class PercentageOffer(){
    private var value: Double = 0.0

    constructor(valuep: Double) : this(){
        this.value = valuep
    }

     fun calculate(montant:Double): Double {
        return montant * (100-value)/100
    }
}
