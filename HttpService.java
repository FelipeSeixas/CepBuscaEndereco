package br.com.api_cepeendereco;
import android.os.AsyncTask;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, CEP>
{
    private final String cepDigitado;

//    RConstrutor - Recebe apenas a numeração do cep digitado na classe Main, em formato de string.
    public HttpService(String cep) {
        this.cepDigitado = cep;
    }

    @Override
    protected CEP doInBackground (Void... voids) {
        StringBuilder resposta = new StringBuilder();

//        Validação do cep: vazio ou quantidade de cracteres desejados
        if (this.cepDigitado != null && this.cepDigitado.length() == 8) {
            try {
//                Declaração da URL e armazenamento do endPoint
                URL url = new URL("https://viacep.com.br/ws/" + this.cepDigitado +"/json");

//                Abertura/Início da conexão
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

//                Características da comunicação (verbo HTTP)
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Gson().fromJson(resposta.toString(), CEP.class);
    }
}
