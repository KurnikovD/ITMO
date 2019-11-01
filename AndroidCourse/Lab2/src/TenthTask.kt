import java.rmi.MarshalledObject

class MoneyConverter{
    fun convertToRubl(f : Money, summ : Double) : Double{
        return f.convertToRubl(summ)
    }
    fun convertTo(f : Money, summ : Double): Double{
        return f.rubliTo(summ)
    }
}

interface Money{
    var coeff : Double
    fun convertToRubl(summ : Double) : Double
    fun rubliTo(summ : Double) : Double
}

class Euro() : Money {
    override var coeff: Double = 70.74


    override fun rubliTo(summ: Double): Double = summ / coeff
    override fun convertToRubl(summ: Double): Double = summ * coeff
}

class Dollar() : Money{
    override var coeff: Double = 60.66

    override fun rubliTo(summ: Double): Double = summ / coeff
    override fun convertToRubl(summ: Double): Double = summ * coeff

}

class Yuan() : Money{
    override var coeff: Double = 9.08

    override fun rubliTo(summ: Double): Double = summ / coeff
    override fun convertToRubl(summ: Double): Double = summ * coeff

}

fun main() {

    println(MoneyConverter().convertToRubl(Yuan(), 33.0))

    println(MoneyConverter().convertTo(Euro(), 141.48))
}