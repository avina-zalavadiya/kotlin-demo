interface CoffeeType {
    fun setCoffeeType():String
}

class BlackCoffee: CoffeeType {
    override fun setCoffeeType():String {
       return "Black"
    }
}

class Latte: CoffeeType {
    override fun setCoffeeType():String {
        return "Latte"
    }
}

class CappuccinoCoffee: CoffeeType {
    override fun setCoffeeType():String{
        return "Cappuccino"
    }
}

interface  Heater {
    fun on() : String
    fun off(): String
    fun isHeating(heating:Boolean):Boolean
}

interface Pump {
    fun pump() : String
}

class ElectricHeater : Heater {
    override fun on(): String {
        return "Electric heater started..."
    }

    override fun off(): String {
        return "Electric heater stopped..."
    }

    override fun isHeating(hetaing: Boolean): Boolean {
       return hetaing;
    }

}


class  WaterPump : Pump {
    override fun pump(): String {
       return "Pump started..."
    }

}


class CoffeeFactory {
    fun  getCoffeyType(coffeeType: String): CoffeeType {
        return when (coffeeType) {
            "BLACK" -> BlackCoffee()
            "LATTE" -> Latte()
            "CAPPUCCINO" -> CappuccinoCoffee()
            else -> throw IllegalArgumentException(
                "Wrong coffee type: $coffeeType"
            )
        }
    }
}


class HeaterFactory {
    fun getHeater(type: String): Heater {
        return when (type) {
            "ELECTRIC" -> ElectricHeater()
            else -> throw IllegalArgumentException(
                "Wrong heater type: $type"
            )
        }
    }
}

class PumpFactory {
    fun getPump(type: String): Pump {
        return when (type) {
            "WATER" -> WaterPump()
            else -> throw IllegalArgumentException(
                "Wrong pump type: $type"
            )
        }
    }
}

class  CoffeeMaking() {
    fun brew(coffeeType: String,heaterType: String,pumpType:String){
        val heaterFactory = HeaterFactory()
        val heater = heaterFactory.getHeater(heaterType)
        println(heater.on())
        val isHeaterStated = heater.isHeating(true)


        val pumpFactory = PumpFactory()
        val pump = pumpFactory.getPump(pumpType)
        if(isHeaterStated){
            println(pump.pump())
        }

        val coffeeFactory = CoffeeFactory()
        val  coffee = coffeeFactory.getCoffeyType(coffeeType);
        println("${coffee.setCoffeeType()} Coffee's Ready! Enjoy" )

        // stop heater and pump
        println(heater.off())
        heater.isHeating(false)
    }


}



fun main(args: Array<String>) {
    val coffeeMaking = CoffeeMaking()
    coffeeMaking.brew("CAPPUCCINO","ELECTRIC","WATER")
}