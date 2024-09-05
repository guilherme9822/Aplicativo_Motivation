package com.example.aplicativomotivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicativomotivation.infra.MotivationConstants
import com.example.aplicativomotivation.R
import com.example.aplicativomotivation.infra.SecurityPreferences
import com.example.aplicativomotivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonsalvar.setOnClickListener(this)

        supportActionBar?.hide()


    }

    override fun onClick(v: View) {
        if (v.id == R.id.buttonsalvar) {
            handleSave()
        }
    }



    private fun handleSave() {
        val name:String = binding.editqual.text.toString()
        if (name != "") {

            SecurityPreferences(this).storeString(MotivationConstants.KEY.PERSON_NAME, name)


            finish()
        } else {
            Toast.makeText(this, "Informe seu Nome!", Toast.LENGTH_SHORT).show()
        }
    }
}