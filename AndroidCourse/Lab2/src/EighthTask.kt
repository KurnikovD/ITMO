open class AirBussA320{
    open fun speed(){
        println("AirBussA320-speed")
    }
    open fun lengthRun(){
        println("AirBussA320-lengthRun")
    }
}

class AirBusA380 : AirBussA320(){
    override fun speed(){
        println("AirBusA380-speed")
    }
    override fun lengthRun(){
        println("AirBusA380-lengthRun")
    }

    fun colOfPas(){
        println("colOfPas")
    }
}