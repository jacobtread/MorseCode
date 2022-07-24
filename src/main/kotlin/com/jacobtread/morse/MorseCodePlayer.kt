package com.jacobtread.morse

import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

class MorseCodePlayer {


    fun playMorseCode(
        value: String,
        space: Char = MorseCode.DEFAULT_SPACE_VALUE,
    ) {
        val timings = parseMorseCode(value, space)
        playSound(timings)
    }

    class MorseCodeTimings(
        val total: Double,
        val individual: List<SoundPoint>,
    )

    data class SoundPoint(var type: Int, var length: Double)

    private fun parseMorseCode(
        value: String,
        space: Char = MorseCode.DEFAULT_SPACE_VALUE,
    ): MorseCodeTimings {

        var timeUnit = 0.08
        var fwTimeUnit = 0.08

        var totalTime = 0.0
        var individual = ArrayList<SoundPoint>()

        value.forEach { char ->
            when (char) {
                '/' -> {
                    val time = 7 * fwTimeUnit
                    totalTime += time
                    individual.add(SoundPoint(0, time))
                }
                '.' -> {
                    totalTime += (timeUnit * 2)
                    individual.add(SoundPoint(1, timeUnit))
                    individual.add(SoundPoint(0, timeUnit))
                }
                '-' -> {
                    totalTime += (timeUnit * 4)
                    individual.add(SoundPoint(1, timeUnit * 3))
                    individual.add(SoundPoint(0, timeUnit))
                }
                else -> {
                    totalTime += (3 * fwTimeUnit)
                    // Gap x7
                    individual.add(SoundPoint(0, 3 * fwTimeUnit))
                }
            }
        }
        return MorseCodeTimings(totalTime, individual)
    }


    private fun playSound(morseCodeTimings: MorseCodeTimings) {
        val sampleRate = 44100

        val seconds = morseCodeTimings.total
        val individual = morseCodeTimings.individual

        val bufferLength = (seconds * sampleRate).toInt()

        val byteBuffer = ByteBuffer.allocate(bufferLength * 4)
            .order(ByteOrder.LITTLE_ENDIAN)


        val iter = individual.iterator()
        while (iter.hasNext()) {
            val value = iter.next()
            val length = value.length * sampleRate

            for (i in 0 until length.toInt()) {
                val v = value.type.toFloat()
                byteBuffer.putFloat(v)
            }
        }

        byteBuffer.flip()

        val inputStream = object : InputStream() {
            override fun read(): Int {
                if (!byteBuffer.hasRemaining()) return -1
                return byteBuffer.get().toInt() and 0xFF
            }

            override fun read(b: ByteArray, off: Int, len: Int): Int {
                if (!byteBuffer.hasRemaining()) return -1
                val newLen = minOf(len, byteBuffer.remaining())
                byteBuffer.get(b, off, newLen)
                return newLen
            }
        }


        val audioFormat = AudioFormat(
            sampleRate.toFloat(),
            1,
            1,
            true,
            false
        )

        val audioStream = AudioInputStream(inputStream, audioFormat, byteBuffer.remaining().toLong())
        val clip = AudioSystem.getClip()
        clip.open(audioStream)
        clip.start()
    }

    fun stop() {

    }

}