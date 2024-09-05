package com.example.aplicativomotivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.aplicativomotivation.infra.MotivationConstants
import com.example.aplicativomotivation.R
import com.example.aplicativomotivation.data.Mock
import com.example.aplicativomotivation.infra.SecurityPreferences
import com.example.aplicativomotivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var securityPreferences: SecurityPreferences

    private var filter: Int = MotivationConstants.FILTER.ALL
    private val mock: Mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        securityPreferences = SecurityPreferences(this)

        handleFilter(R.id.viewIfinit)
        handNextPhrase()

        binding.buttonNovaFrase.setOnClickListener(this)
        binding.viewHappy.setOnClickListener(this)
        binding.viewsunny.setOnClickListener(this)
        binding.viewIfinit.setOnClickListener(this)
        binding.textola.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        showUserName()

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(view: View) {
        val id: Int = view.id

        val listId = listOf(
            R.id.viewIfinit,
            R.id.viewHappy,
            R.id.viewsunny
        )

        if (id in listId) {
            handleFilter(id)
        }else if (id == R.id.button_novaFrase){
            refreshPhrase()
        }else if (id == R.id.textola){
            startActivity(Intent(this, UserActivity::class.java))
        }
    }
    private fun refreshPhrase() {
        binding.motivation.text = mock.getPhrase(filter)
    }

    private fun showUserName() {
        val user = securityPreferences.getStoredString(MotivationConstants.KEY.PERSON_NAME)
        binding.textola.text = "Olá, $user!"
    }


    private fun handNextPhrase(){
        binding.motivation.text = Mock().getPhrase(filter)
    }

    private fun handleFilter(id: Int){

        binding.viewIfinit.setColorFilter(ContextCompat.getColor(this, R.color.button))
        binding.viewsunny.setColorFilter(ContextCompat.getColor(this, R.color.button))
        binding.viewHappy.setColorFilter(ContextCompat.getColor(this, R.color.button))
        when (id) {
            R.id.viewIfinit -> {
                binding.viewIfinit.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstants.FILTER.ALL
            }
            R.id.viewsunny -> {
                binding.viewsunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstants.FILTER.SUNNY
            }
            R.id.viewHappy -> {
                binding.viewHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstants.FILTER.HAPPY
            }
        }
    }



}