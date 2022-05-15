package br.com.api_cepeendereco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

//    Declaração da entrada do cep e saída do endereço
    EditText txtCep;

    TextView txtResultado;

    Button btnBuscarCep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Vinculação das views
        txtCep = findViewById(R.id.editTextNumber);
        txtResultado = findViewById(R.id.lblResposta);

        btnBuscarCep = findViewById(R.id.btnBuscaCep);

//        Tentativa de leitura do webservice e exibição do endereço
        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    Conexão e inicialização das variáveis que compõem o endereço completo
                    CEP cep = new HttpService(txtCep.getText().toString().trim()).execute().get();

//                    Retorna o endereço completo (conjunto de 6 strings) numa única String
                    txtResultado.setText(cep.toString());

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}