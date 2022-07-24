package com.jacobtread.morse

import kotlin.test.Test

class TestSound {

    fun `test sound`() {
        MorseCodePlayer()
            .playMorseCode(". -..- .- -- .--. .-.. . / - . -..- -")
    }

}