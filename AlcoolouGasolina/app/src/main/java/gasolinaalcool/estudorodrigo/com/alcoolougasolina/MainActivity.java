package gasolinaalcool.estudorodrigo.com.alcoolougasolina;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private EditText edtPrecoAlcool;
    private EditText edtPrecoGasolina;
    private Button btnBotao;
    private TextView txtResultado;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPrecoAlcool = (EditText) findViewById(R.id.precoAlcool);
        edtPrecoGasolina = (EditText) findViewById(R.id.precoGasolina);
        btnBotao = (Button) findViewById(R.id.botaoid);
        txtResultado = (TextView) findViewById(R.id.resultbotao);

        btnBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recuperar os valores digitados
                String sPrecoAlcool = edtPrecoAlcool.getText().toString();
                String sPrecoGasolina = edtPrecoGasolina.getText().toString();

                if (sPrecoAlcool.isEmpty()||sPrecoGasolina.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Informe o preço do Álcool e da Gasolina",Toast.LENGTH_LONG).show();
                }else{
                    double fValorPercentual = Double.parseDouble(sPrecoAlcool) / Double.parseDouble(sPrecoGasolina);

                    if (fValorPercentual > 0.7){
                        txtResultado.setText("Abasteça Gasolina!");
                    }else{
                        txtResultado.setText("Abasteça Álcool!");
                    }
                }

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
