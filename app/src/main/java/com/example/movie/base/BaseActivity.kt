package com.example.movie.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: B

    abstract val setLayout: (LayoutInflater) -> B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setLayout.invoke(layoutInflater)
        setContentView(binding.root)

        initialization()
        observeViewModel()
    }

    abstract fun initialization()

    abstract fun observeViewModel()


}