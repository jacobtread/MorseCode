import kotlin.random.Random
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

    @Test
    fun `encode text with punctuation`() {
        val expected = ". -..- .- -- .--. .-.. . / - . -..- - .-.-.- .-.-.- ..--.."
        val text = "Example Text..?"
        val result = MorseCode.encodeText(text)
        assertEquals(expected, result)
    }


    @Test
    fun `decode text with punctuation`() {
        val expected = "EXAMPLE TEXT..?"
        val morseCode = ". -..- .- -- .--. .-.. . / - . -..- - .-.-.- .-.-.- ..--.."
        val result = MorseCode.decodeText(morseCode)
        assertEquals(expected, result)
    }

    @Test
    fun `encode text with numbers`() {
        val expected = ". -..- .- -- .--. .-.. . / - . -..- - / .---- ..--- ...--"
        val text = "Example Text 123"
        val result = MorseCode.encodeText(text)
        assertEquals(expected, result)
    }


    @Test
    fun `decode text with numbers`() {
        val expected = "EXAMPLE TEXT 123"
        val morseCode = ". -..- .- -- .--. .-.. . / - . -..- - / .---- ..--- ...--"
        val result = MorseCode.decodeText(morseCode)
        assertEquals(expected, result)
    }

    @Test
    fun `encode & decode`() {
        val value = "EXAMPLE TEXT 123"
        val encoded = MorseCode.encodeText(value)
        val decoded = MorseCode.decodeText(encoded)
        assertEquals(value, decoded)
    }

    @Test
    fun `encode & decode large`() {
        val iterations = 100
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
            .toCharArray()

        repeat(iterations) {
            val valueBuilder = StringBuilder()
            val valueLength = Random.nextInt(1, 50)
            repeat(valueLength) { valueBuilder.append(chars.random()) }
            val value = valueBuilder.toString()

            val encoded = MorseCode.encodeText(value)
            val decoded = MorseCode.decodeText(encoded)

            assertEquals(value, decoded)
        }
    }


}