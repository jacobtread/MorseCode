package com.jacobtread.morse

import kotlin.test.Test

class TestSound {

    @Test
    fun `test sound`() {
        MorseCodePlayer()
            .playMorseCode(". -..- .- -- .--. .-.. . / - . -..- -")
    }

}