import kotlin.test.Test
import kotlin.test.assertEquals

class TestEncode {

    @Test
    fun `encode plain text`() {
        val expected = ". -..- .- -- .--. .-.. . / - . -..- -"
        val text = "Example Text"
        val result = MorseCode.encodeText(text)
        assertEquals(expected, result)
    }


    @Test
    fun `decode plain text`() {
        val expected = "EXAMPLE TEXT"
        val morseCode = ". -..- .- -- .--. .-.. . / - . -..- -"
        val result = MorseCode.decodeText(morseCode)
        assertEquals(expected, result)
    }
}