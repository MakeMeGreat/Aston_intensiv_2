package com.example.aston_intensive_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.request.CachePolicy
import coil.size.Scale
import com.example.aston_intensive_2.databinding.ActivityMainBinding

private const val ONE_COLOR_ANGLE = 51.42857f

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var lastRotation = 0f //TODO: use to screen orientation change
    private var rotationsCount = 0
    private var isImage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rotateButton.setOnClickListener {
            binding.circle.animate().rotation(getRotationAngle()).setDuration(2500)
                .withStartAction {
                    binding.rotateButton.isEnabled = false
                }
                .withEndAction {
                    binding.rotateButton.isEnabled = true
                    setTextOrImage()
                }
        }
        binding.resetButton.setOnClickListener {
            binding.imageHolder.setImageResource(0)
            binding.textHolder.drawText("")
        }
    }

    private fun getRotationAngle(): Float {
        val randomNumber = getRandomNumber()
        determineIsImage(randomNumber)
        val rotationAngle = randomNumber * ONE_COLOR_ANGLE
        //Todo: add variable to scrnOrient change
        return rotationAngle + rotationsCount++ * 360f
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

    private fun setTextOrImage() {
        if (isImage) {
            binding.imageHolder.load("https://placebeard.it/640x360") {
                scale(Scale.FILL)
                placeholder(R.drawable.image_holder)
                memoryCachePolicy(CachePolicy.DISABLED)
                error(R.drawable.error_img)
            }
        } else {
            binding.textHolder.drawText("rotataions count: $rotationsCount")
        }
    }
}