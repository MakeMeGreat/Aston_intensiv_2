package com.example.aston_intensive_2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.size.Scale
import com.example.aston_intensive_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rotateButton.setOnClickListener {
            binding.circle.animate().rotation(viewModel.getRotationAngle()).setDuration(2500)
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
            viewModel.clearIsImage()
        }
    }

    private fun setTextOrImage() {
        if (viewModel.isImage()) {
            binding.imageHolder.load("https://placebeard.it/640x360") {
                scale(Scale.FILL)
                placeholder(R.drawable.image_holder)
                error(R.drawable.error_img)
            }
        } else {
            binding.textHolder.drawText("rotataions count: ${viewModel.getRotationsCount()}")
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.circle.rotation = viewModel.getLastRotation()
        setTextOrImage()
    }
}