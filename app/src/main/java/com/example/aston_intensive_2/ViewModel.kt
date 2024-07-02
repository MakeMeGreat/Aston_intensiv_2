package com.example.aston_intensive_2

import androidx.lifecycle.ViewModel

private const val ONE_COLOR_ANGLE = 51.42857f

class ViewModel : ViewModel() {

    private var lastRotation = 0f
    private var rotationsCount = 0
    private var isImage = false

    fun getRotationAngle(): Float {
        val randomNumber = getRandomNumber()
        determineIsImage(randomNumber)
        val rotationAngle = randomNumber * ONE_COLOR_ANGLE
        val angleWithPreviousRotations = rotationAngle + rotationsCount++ * 360f
        lastRotation = angleWithPreviousRotations
        return angleWithPreviousRotations
    }

    private fun getRandomNumber(): Int {
        val possibleValues = listOf(1, 2, 3, 4, 5, 6, 7)
        return possibleValues.random()
    }

    private fun determineIsImage(int: Int) {
        isImage = when (int) {
            2, 4, 6 -> true
            else -> false
        }
    }

    fun isImage() = isImage

    fun clearIsImage() {
        isImage = false
    }

    fun getRotationsCount() = rotationsCount

    fun getLastRotation() = lastRotation
}