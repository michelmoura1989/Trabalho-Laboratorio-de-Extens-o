package com.example.calculadoimc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referências dos elementos da tela
        val etPeso = findViewById<EditText>(R.id.edtPeso)
        val etAltura = findViewById<EditText>(R.id.edtAltura)
        val rgSexo = findViewById<RadioGroup>(R.id.radioGroupSexo)
        val rbMasculino = findViewById<RadioButton>(R.id.radioMasculino)
        val rbFeminino = findViewById<RadioButton>(R.id.radioFeminino)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnLimpar = findViewById<Button>(R.id.btnLimpar)
        val tvResultado = findViewById<TextView>(R.id.textResultado)

        // Botão Calcular IMC
        btnCalcular.setOnClickListener {
            val pesoStr = etPeso.text.toString()
            val alturaStr = etAltura.text.toString()

            if (pesoStr.isNotEmpty() && alturaStr.isNotEmpty()) {
                val peso = pesoStr.toFloat()
                val altura = alturaStr.toFloat()

                val imc = peso / (altura * altura)

                val sexo = when {
                    rbMasculino.isChecked -> "Masculino"
                    rbFeminino.isChecked -> "Feminino"
                    else -> "Não informado"
                }

                val classificacao = when {
                    imc < 18.5 -> "Abaixo do peso"
                    imc < 24.9 -> "Peso normal"
                    imc < 29.9 -> "Sobrepeso"
                    imc < 34.9 -> "Obesidade Grau I"
                    imc < 39.9 -> "Obesidade Grau II"
                    else -> "Obesidade Grau III"
                }

                tvResultado.text = "Sexo: $sexo\nIMC: %.2f\nClassificação: %s"
                    .format(imc, classificacao)

            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
        // Botão Limpar Campos

        btnLimpar.setOnClickListener {
            etPeso.text.clear()
            etAltura.text.clear()
            rgSexo.clearCheck()
            tvResultado.text = "Resultado aparecerá aqui"
        }
    }
}
