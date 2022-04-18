fun main(args: Array<String>) {
        
        val trains = mapOf(
            '0' to listOf("Train1", "Surat", "Ahmedabad", "09:00AM"),
            '1' to listOf("Train2", "Pune", "Bangloar", "11:00AM"),
            '2' to listOf("Train3", "Mumbai", "Ahmedabad", "12:00AM"),
            '2' to listOf("Train4", "Surat", "Mumbai", "12:00AM"),
        )
                
           val getKeyIndex: Int = when(args[0]) {
            "name" -> 0
            "source" -> 1
            "destination" -> 2
            "departureTime" -> 3
            else -> -1
        }        
        
        var result: Boolean = false
        for ((key, value) in trains) {
            result = getTrains(args[1], getKeyIndex, value)
            if(result) {
                 println("Trains : $value")
            }
        }    
   }
 
fun getTrains(value: String, index: Int, train: List<String>): Boolean {
    for (arrIndex in train.indices) {
        if(arrIndex === index && train[arrIndex].uppercase() == value.uppercase()) {
            return true
        }
    }
    return false
}
