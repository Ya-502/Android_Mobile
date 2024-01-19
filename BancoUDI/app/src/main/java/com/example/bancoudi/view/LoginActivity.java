package com.example.bancoudi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.example.bancoudi.controller.ControllerBancoDados;
import com.example.bancoudi.databinding.ActivityLoginBinding;
import com.example.bancoudi.model.DadosBancarios;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;
    private DadosBancarios dadosBancarios;
    private ControllerBancoDados controllerBancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dadosBancarios = new DadosBancarios();

        controllerBancoDados = new ControllerBancoDados(this);
        controllerBancoDados.open();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        binding.BTNentrarNoApp.setOnClickListener(v -> {
            String nome = binding.EntradaNomeHint.getText().toString().trim();
            String saldo = binding.EntradaSaldoHint.getText().toString().trim();


            if (!saldo.isEmpty() && !nome.isEmpty()) {
                try {
                    Double saldoConverte = Double.parseDouble(saldo);
                    dadosBancarios.setSaldo(saldoConverte);
                    dadosBancarios.setTitular(nome);

                    controllerBancoDados.insertData(nome, saldo);

                    intent.putExtra("titular", nome);
                    intent.putExtra("saldo", saldo);

                    startActivity(intent);
                    finish();

                }catch (Exception e){
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(getApplicationContext(), "UM DOS CAMPOS ESTÁ VAZIO", Toast.LENGTH_LONG).show();
            }


        });

    }
}