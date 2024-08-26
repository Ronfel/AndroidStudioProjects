package atmempreaconsultoria.estudorodrigo.com.atmempresaconsultoria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView btnEmpresa;
    private ImageView btnClientes;
    private ImageView btnServicos;
    private ImageView btnContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmpresa = (ImageView) findViewById(R.id.empresaID);
        btnClientes = (ImageView) findViewById(R.id.clientesID);
        btnServicos = (ImageView) findViewById(R.id.sevicosID);
        btnContato = (ImageView) findViewById(R.id.contatoID);

        btnEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EmpresaActivity.class));
            }
        });

        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ClientesActivity.class));
            }
        });
        btnServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServicoActivity.class));
            }
        });
        btnContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContatoActivity.class));
            }
        });
    }
}
