import kotlin.math.exp
import kotlin.test.Test
import kotlin.test.assertEquals

class TestEncode {

    @Test
    fun `encode plain text`() {
        val expected = ". -..- .- -- .--. .-.. . / - . -..- -"

        val text = "Example Text"
        val result = MorseCode.encodeText(text)

        assertEquals(expected, result)

        println(result)
    }

}