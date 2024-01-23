package com.example.idavolta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.idavolta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var result: ActivityResultLauncher<Intent>
    private var saldo = "nome"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BtnMudarSaldo.setOnClickListener {
            val i = Intent(this, MainActivity2::class.java)
            i.putExtra("saldo", saldo)

            result.launch(i)

        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.data != null && it.resultCode == 1){
                saldo = it.data?.getStringExtra("saldo").toString()
                binding.TextSaldo.text = "$saldo"
            }else{
                Toast.makeText(applicationContext, "ERRO", Toast.LENGTH_LONG).show()
            }
        }

    }
}