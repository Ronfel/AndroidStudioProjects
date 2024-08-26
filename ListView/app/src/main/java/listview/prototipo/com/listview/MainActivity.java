package listview.prototipo.com.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listaItens;
    private String[] itens = {
            "Angra dos Reis", "Caldas Novas",
            "Campos do Jordão", "Águas de São Pedro",
            "Florianópolis", "Miami", "Paris",
            "Caribe", "Montivideu", "Rio de Janeiro",
            "Madri", "Roma", "Belo Horizonte"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaItens = (ListView) findViewById(R.id.listViewId);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );
        listaItens.setAdapter(adaptador);
    }
}
