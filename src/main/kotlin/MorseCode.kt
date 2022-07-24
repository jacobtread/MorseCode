object MorseCode {

    private val mapping: Map<Char, String> = mapOf(
        'A' to ".-", 'B' to "-...", 'C' to "-.-.", 'D' to "-..", 'E' to ".",
        'F' to "..-.", 'G' to "--.", 'H' to "....", 'I' to "..", 'J' to ".---",
        'K' to "-.-", 'L' to ".-..", 'M' to "--", 'N' to "-.", 'O' to "---",
        'P' to ".--.", 'Q' to "--.-", 'R' to ".-.", 'S' to "...", 'T' to "-",
        'U' to "..-", 'V' to "...-", 'W' to ".--", 'X' to "-..-", 'Y' to "-.--",
        'Z' to "--..",

        '0' to "-----", '1' to ".----", '2' to "..---", '3' to "...--", '4' to "....-",
        '5' to ".....", '6' to "-....", '7' to "--...", '8' to "---..", '9' to "----.",

        '.' to ".-.-.-", ',' to "--..--", '?' to "..--..", '\'' to ".----.", '!' to "-.-.--",
        '/' to "-..-.", '(' to "-.--.", ')' to "-.--.-", '&' to ".-...", ':' to "---...",
        ';' to "-.-.-.", '=' to "-...-", '+' to ".-.-.", '-' to "-....-", '_' to "..--.-",
        '"' to ".-..-.", '$' to "...-..-", '@' to ".--.-.", '¿' to "..-.-", '¡' to "--...-",

        ' ' to "/"
    )

    private const val INVALID_VALUE: String = "#"
    private const val SEPARATOR: String = " "

    fun encodeText(value: String): String {
        val output = StringBuilder()
        val lastIndex = value.length - 1
        value.forEachIndexed { index, char ->
            val mappedValue: String? = if (char.isLetter()) {
                val upperCase = char.uppercaseChar()
                mapping[upperCase]
            } else {
                mapping[char]
            }
            if (mappedValue != null) {
                output.append(mappedValue)
            } else {
                output.append(INVALID_VALUE)
            }
            if (index != lastIndex) {
                output.append(SEPARATOR)
            }
        }
        return output.toString()
    }

}