package fr.android.paintersimon.main.domain.commercialOffer

class SliceOffer() {
    private var value: Double = 0.0
    private var sliceValue: Double  = 0.0

    constructor(valuep: Double, sliceValuep: Double) : this(){
        this.value = valuep
        this.sliceValue = sliceValuep
    }

     fun calculate(montant:Double): Double {
        val numberOfSlices: Int = (montant/sliceValue).toInt()
        return montant-value*numberOfSlices
    }
}
