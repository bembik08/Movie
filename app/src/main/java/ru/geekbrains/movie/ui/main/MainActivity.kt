package ru.geekbrains.movie.ui.main

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.geekbrains.movie.R
import ru.geekbrains.movie.base.BottomNavigationListener
import ru.geekbrains.movie.databinding.ActivityMainBinding
import ru.geekbrains.movie.utils.hide
import ru.geekbrains.movie.utils.show
import ru.geekbrains.movie.utils.showToast

class MainActivity : AppCompatActivity(), BottomNavigationListener {

    private val navController by lazy { findNavController(R.id.navHostFragment) }
    private lateinit var binding: ActivityMainBinding
    private var sayBackPress = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    override fun onBackPressed() {
        if (sayBackPress + 3000 > System.currentTimeMillis()) super.onBackPressed() else {
            showToast(R.string.label_press_back_once_again)
            sayBackPress = System.currentTimeMillis()
        }
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        navController.popBackStack()
    }

    private fun setUpView() {
        binding.navView.setupWithNavController(navController)
    }

    override fun showNav() {
        binding.navView.show()
    }

    override fun hideNav() {
        binding.navView.hide()
    }
}
