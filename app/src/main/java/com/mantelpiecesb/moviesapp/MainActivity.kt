package com.mantelpiecesb.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import com.mantelpiecesb.moviesapp.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            _binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(mBinding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}