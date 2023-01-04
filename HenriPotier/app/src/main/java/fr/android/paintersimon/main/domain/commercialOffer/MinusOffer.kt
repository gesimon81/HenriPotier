package fr.android.paintersimon.main.domain.commercialOffer

class MinusOffer()  {
    private var value: Double = 0.0

    constructor(valuep: Double) : this(){
        this.value = valuep
    }

     fun calculate(montant:Double): Double {
        return (montant - value)
    }
}
