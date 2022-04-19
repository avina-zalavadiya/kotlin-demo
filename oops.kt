interface Beverages {
    fun getMixture(ingredients:List<String>){
        for (item in ingredients){
            println("Adding $item")
        }
    }

     fun cleanPot(){
        println("Your Beverage is getting ready...")
    }
}

class Coffee():Beverages {

    fun addIngredients(){
        println("Coffee Bag added")
    }

    fun getBeverage(){
        println( "Coffee's Ready! Enjoy")
    }

    override fun cleanPot(){
        println("Cleaning Coffee pot...")
        super.cleanPot()
    }

}

class Tea():Beverages {
    fun addIngredients(){
        println("Tea Bag added")
    }

    fun getBeverage(){
        println( "Tea's Ready! Enjoy")
    }

    override fun cleanPot(){
        println("Cleaning Tea pot...")
        super.cleanPot()
    }

}

fun main(args: Array<String>) {
    val ingredientsList = listOf("hotwater","milk","sugar")
    println("Want Beverage? Enter 1 for Tea/ 2 for Coffee!")

    val stringInput = readLine()!!
    if((stringInput.toInt()) === 2){
        val coffee = Coffee()
        coffee.cleanPot()
        coffee.getMixture(ingredientsList)
        coffee.addIngredients()
        coffee.getBeverage()
    }else{
        val tea = Tea()
        tea.cleanPot()
        tea.getMixture(ingredientsList)
        tea.addIngredients()
        tea.getBeverage()
    }
}
