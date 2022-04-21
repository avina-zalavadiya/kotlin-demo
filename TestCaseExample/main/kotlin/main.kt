val snakeRegex = "_[a-zA-Z]".toRegex()
fun String.snakeToLowerCamelCase(): String {
    return snakeRegex.replace(this) {
        it.value.replace("_","")
            .toUpperCase()
    }
}

fun String.snakeToUpperCamelCase(): String {
    return this.snakeToLowerCamelCase().capitalize()
}

class GenerateName(private val techName:String, private val screenName: String){
    fun generateLayoutFile(): String {
       if(techName === "ANDROID") {
           return "activity_${screenName.replace(" ", "_").toLowerCase()}"
       }else if(techName === "FLUTTER"){
           return screenName.replace(" ", "_").toLowerCase()
       }
       return ""
   }

    fun generateControllerFile(): String{

        if(techName === "ANDROID") {
            val snakeCaseName = "${screenName.replace(" ", "_").toLowerCase()}Activity"
            return snakeCaseName.snakeToUpperCamelCase()
        }else if(techName === "FLUTTER"){
            return "${screenName.replace(" ", "_").toLowerCase()}_controller"
        }
        return ""
    }

    fun getModelName(): String {
        if(techName === "ANDROID") {
            val snakeCaseName = "${screenName.replace(" ", "_").toLowerCase()}Model"
            return snakeCaseName.snakeToUpperCamelCase()
        }else if(techName === "FLUTTER"){
            return "${screenName.replace(" ", "_").toLowerCase()}_model"
        }else if(techName === "IOS"){
            val snakeCaseName = "${screenName.replace(" ", "_").toLowerCase()}ViewModel"
            return snakeCaseName.snakeToUpperCamelCase()
        }
        return ""
    }

    fun getViewFile():String{
        if(techName === "IOS") {
            val snakeCaseName = "${screenName.replace(" ", "_").toLowerCase()}View"
            return snakeCaseName.snakeToUpperCamelCase()
        }
        return ""
    }
}


fun main() {
    val generateName = GenerateName("IOS","Splash Screen");
    val layoutFileName = generateName.generateLayoutFile()
    val controllerFile = generateName.generateControllerFile()
    val modelName = generateName.getModelName()
    val viewFile = generateName.getViewFile()
    println(layoutFileName)
    println(controllerFile)
    println(viewFile);
    println(modelName)
}