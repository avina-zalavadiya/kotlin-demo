import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


fun isContainNumber(toCheck: String): Boolean {
    val regex = "^[A-Za-z_ ]+".toRegex()
    return toCheck.matches(regex)
}

internal class GenerateNameTest {


    private val testGenerateName: GenerateName = GenerateName("ANDROID","splash screen")

    @Test
    fun testGenerateLayoutFile() {
        val expected = "activity_splash_screen"
        assertTrue(isContainNumber(testGenerateName.generateLayoutFile()))
        assertEquals(expected, testGenerateName.generateLayoutFile())
        assertNotNull(testGenerateName.generateLayoutFile())

    }

    @Test
    fun testEmptyGenerateLayoutFile() {
        val expected = ""
        assertNotEquals(expected, testGenerateName.generateLayoutFile(),"Empty screen name")
    }

    @Test
    fun testGenerateControllerFile() {
        val expected = "SplashScreenActivity"
        assertEquals(expected, testGenerateName.generateControllerFile())
    }

    @Test
    fun testGetModelName() {
        val expected = "SplashScreenModel"
        assertEquals(expected, testGenerateName.getModelName())
    }

    @Test
    fun getViewFile() {
        val expected = ""
        assertEquals(expected, testGenerateName.getViewFile())
    }
}