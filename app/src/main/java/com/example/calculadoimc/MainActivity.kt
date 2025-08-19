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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtPeso = findViewById<EditText>(R.id.edtPeso)
        val edtAltura = findViewById<EditText>(R.id.edtAltura)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupSexo)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnLimpar = findViewById<Button>(R.id.btnLimpar)
        val txtResultado = findViewById<TextView>(R.id.textResultado)

        btnCalcular.setOnClickListener {
            val peso = edtPeso.text.toString().toFloatOrNull()
            val altura = edtAltura.text.toString().toFloatOrNull()
            val sexoId = radioGroup.checkedRadioButtonId

            if (peso != null && altura != null && sexoId != -1) {
                val imc = peso / (altura * altura)
                val sexo = findViewById<RadioButton>(sexoId).text.toString()
                val classificacao = when {
                    imc < 18.5 -> "Abaixo do peso"
                    imc < 24.9 -> "Peso normal"
                    imc < 29.9 -> "Sobrepeso"
                    imc < 34.9 -> "Obesidade grau I"
                    imc < 39.9 -> "Obesidade grau II"
                    else -> "Obesidade grau III"
                }
                txtResultado.text = "Sexo: $sexo\nIMC: %.2f\n$classificacao".format(imc)
            } else {
                txtResultado.text = "Preencha todos os campos corretamente"
            }
        }
    }
}