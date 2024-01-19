package com.example.bancoudi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bancoudi.R;
import com.example.bancoudi.controller.ControllerBancoDados;
import com.example.bancoudi.databinding.ActivityMainBinding;
import com.example.bancoudi.model.DadosBancarios;
import com.example.bancoudi.model.ModelBancoDados;

import org.jetbrains.annotations.Contract;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ControllerBancoDados controllerBancoDados;
    private ModelBancoDados modelBancoDados;
    private DadosBancarios dadosBancarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        controllerBancoDados = new ControllerBancoDados(this);
        controllerBancoDados.open();

        modelBancoDados = new ModelBancoDados(this);

        dadosBancarios = new DadosBancarios();

        Intent intent = getIntent();

        String titular = intent.getStringExtra("titular");
        String saldo = intent.getStringExtra("saldo");

        Double saldoDouble = Double.parseDouble(saldo);

        dadosBancarios.setTitular(titular);
        dadosBancarios.setSaldo(saldoDouble);

        if (titular != null) {
            binding.NomeUsuario.setText(titular);
        } else {
            binding.NomeUsuario.setText("Titular Não Definido");
        }

        if (saldo != null) {
            binding.SaldoUsuario.setText(String.valueOf(saldo));
        } else {
            binding.SaldoUsuario.setText("Saldo Não Definido");
        }


        binding.BTNDepositar.setOnClickListener(v -> {
            String valorTxt = binding.ValorDinheiroUser.getText().toString().trim();
            String saldoAtual = binding.SaldoUsuario.getText().toString();

            if (!valorTxt.isEmpty()){
                try {

                    Double valorDouble = Double.parseDouble(valorTxt);
                    Double saldoAtualDouble = Double.parseDouble(saldoAtual);

                    Double saldoFinal = saldoAtualDouble + valorDouble;

                    binding.SaldoUsuario.setText(String.valueOf(saldoFinal));
                    dadosBancarios.setSaldo(saldoFinal);

                    controllerBancoDados.updateSaldo(titular, saldoAtual);

                } catch (NumberFormatException e){
                    e.printStackTrace();
                }

            }else {
                Toast.makeText(getApplicationContext(), "Valor Vazio", Toast.LENGTH_SHORT).show();
            }

            binding.ValorDinheiroUser.setText("");

        });

        binding.BTNSacar.setOnClickListener(v -> {
            String valorTxt = binding.ValorDinheiroUser.getText().toString().trim();
            String saldoAtual = binding.SaldoUsuario.getText().toString();

            if (!valorTxt.isEmpty()){
                try {

                    Double valorDouble = Double.parseDouble(valorTxt);
                    Double saldoAtualDouble = Double.parseDouble(saldoAtual);

                    Double saldoFinal = saldoAtualDouble - valorDouble;

                    binding.SaldoUsuario.setText(String.valueOf(saldoFinal));
                    dadosBancarios.setSaldo(saldoFinal);

                    controllerBancoDados.updateSaldo(titular, saldoAtual);

                } catch (NumberFormatException e){
                    e.printStackTrace();
                }

            }else {
                Toast.makeText(getApplicationContext(), "Valor Vazio", Toast.LENGTH_SHORT).show();
            }

            binding.ValorDinheiroUser.setText("");

        });



    }
}