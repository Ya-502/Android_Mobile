package com.example.idavolta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.idavolta.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val saldo = i.extras?.getString("saldo")

        if (saldo != null) {
            binding.EditSaldo.setText(saldo)
        }

        binding.BtnMudar.setOnClickListener {
            i.putExtra("saldo", binding.EditSaldo.text.toString().toInt())
        }
    }
}