package com.example.calculadoradecreditos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText campoValor, campoCuotas, campoInteres;
    private Button btnCalcular;
    private TextView tvValorCuota, tvValorTotal, tvGanancia;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        campoValor = findViewById(R.id.campoValor);
        campoCuotas = findViewById(R.id.campoCuotas);
        campoInteres = findViewById(R.id.campoInteres);
        btnCalcular = findViewById(R.id.btnCalcular);
        tvValorCuota = findViewById(R.id.tvValorCuota);
        tvValorTotal = findViewById(R.id.tvValorTotal);
        tvGanancia = findViewById(R.id.tvGanancia);

        btnCalcular.setOnClickListener(v -> calcular());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    private void calcular() {
        String textoValor = campoValor.getText().toString().trim();
        String textoCuotas = campoCuotas.getText().toString().trim();
        String textoInteres = campoInteres.getText().toString().trim();

        if (textoValor.isEmpty() || textoCuotas.isEmpty() || textoInteres.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double valorCredito;
        int numeroCuotas;
        double interesMensual;

        try {
            valorCredito = Double.parseDouble(textoValor);
            numeroCuotas = Integer.parseInt(textoCuotas);
            interesMensual = Double.parseDouble(textoInteres);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingresa valores numéricos válidos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (numeroCuotas <= 0) {
            Toast.makeText(this, "El número de cuotas debe ser mayor a 0", Toast.LENGTH_SHORT).show();
            return;
        }

        double interesTotal = valorCredito * (interesMensual / 100) * numeroCuotas;
        double valorTotal = valorCredito + interesTotal;
        double valorCuota = valorTotal / numeroCuotas;

        tvValorCuota.setText(String.format("Valor por cuota: %.2f", valorCuota));
        tvValorTotal.setText(String.format("Valor total del crédito: %.2f", valorTotal));
        tvGanancia.setText(String.format("Ganancia total: %.2f", interesTotal));
    }
}