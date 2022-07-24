package com.jacobtread.morse

/**
 * Utility class for encoding and decoding morse code
 *
 * @see encodeText For encoding text to morse code
 * @see decodeText For decoding morse code to text
 */
object MorseCode {

    /**
     * This map is a mapping between standard uppercase characters, numbers,
     * and punctuation with their morse code representation
     */
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
    )

    /**
     * Lazy loaded inverse of the mapping map. This is used to convert text
     * back from morse code into human-readable text. This is lazy loaded so
     * that two maps don't have to be loaded
     */
    private val lazyInverseMap: Map<String, Char> by lazy {
        val reverseMap = HashMap<String, Char>()
        mapping.forEach { (key, value) ->
            reverseMap[value] = key
        }
        reverseMap
    }

    private const val DEFAULT_INVALID_VALUE: String = "#"
    private const val DEFAULT_SPACE_VALUE: String = "/"
    private const val DEFAULT_SEPARATOR: String = " "

    /**
     * Encodes the provided text into its morse code representation using
     * the provided [invalid], [space], and [separator] values
     *
     * @param value The value to encode to morse code
     * @param invalid The value that should be used in place of invalid characters
     * @param space The value that should be used to represent a space (Defaults to "/")
     * @param separator The value that should be placed between each piece of morse code
     * @return The encoded morse code value
     */
    @JvmOverloads
    fun encodeText(
        value: String,
        invalid: String = DEFAULT_INVALID_VALUE,
        space: String = DEFAULT_SPACE_VALUE,
        separator: String = DEFAULT_SEPARATOR,
    ): String {
        val output = StringBuilder()
        val lastIndex = value.length - 1
        value.forEachIndexed { index, char ->
            if (char == ' ') { // Replace space characters
                output.append(space)
            } else {
                // Lookup the uppercase character mapping
                val uppercaseChar = char.uppercaseChar()
                // Default to invalid character if not found
                val mappedValue = mapping[uppercaseChar] ?: invalid
                output.append(mappedValue)
            }

            // Append the separator if this is not the last value
            if (index != lastIndex) {
                output.append(separator)
            }
        }
        return output.toString()
    }

    /**
     * Decodes the provided morse code into human-readable text using
     * the provided [invalid], [space], and [separator] values.
     *
     * NOTE: Values lose their casing when encoded to morse code so any
     * characters produced from this function will be in uppercase
     *
     * @param value The morse code value to decrease
     * @param invalid The value that should be used in place of invalid characters
     * @param space The value that was used to represent a space (Defaults to "/")
     * @param separator The value that was placed between each piece of morse code
     * @return The encoded morse code value
     */
    @JvmOverloads
    fun decodeText(
        value: String,
        invalid: String = DEFAULT_INVALID_VALUE,
        space: String = DEFAULT_SPACE_VALUE,
        separator: String = DEFAULT_SEPARATOR,
    ): String {
        val output = StringBuilder()
        val spaces = value.split(space) // Split the values based on spaces
        val lastSpaceIndex = spaces.size - 1
        spaces.forEachIndexed { spaceIndex, spaceBetween ->
            // Split the values based on each character of morse code
            val parts = spaceBetween.split(separator)
            parts.forEach {
                if (it.isNotEmpty()) { // Ignore empty strings from split
                    // Lookup the morse code in the lazy inverse map
                    val character = lazyInverseMap[it]
                    if (character != null) {
                        // Append the mapped character
                        output.append(character)
                    } else {
                        // Append the invalid character
                        output.append(invalid)
                    }
                }
            }

            // Append the space if this is not the last value
            if (spaceIndex != lastSpaceIndex) {
                output.append(' ')
            }
        }
        return output.toString()
    }
}