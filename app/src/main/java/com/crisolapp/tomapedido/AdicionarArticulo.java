package com.crisolapp.tomapedido;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.crisolapp.DB.modeloInsumos;
import com.crisolapp.R;
import com.crisolapp.librerias.MyActividad;

import java.util.Locale;

public class AdicionarArticulo extends MyActividad implements AdapterView.OnItemClickListener,TextWatcher {

    modeloInsumos modeloArticulos ;
    ListView lista;
    AdaptadoArticulos[] adaptadoArticulos;
    TextView inputCodigo;
    TextView inputNombre;
    AdaptadorArticulos ada;
    Bundle bolsa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tomapedido_activity_adicionar_articulo);
        lista = (ListView)findViewById(R.id.ArticuloList);

        lista.setOnItemClickListener(this);
        inputCodigo = (TextView)findViewById(R.id.inputCodigo);
        inputNombre = (TextView)findViewById(R.id.inputNombre);

        modeloArticulos = new modeloInsumos(this);
        bolsa = getIntent().getExtras();
        Cursor c;
        c = modeloArticulos.buscar(null, null);
        if(c.moveToFirst()){
            int i=0;
            System.out.println("cantidad.........."+c.getCount());
            adaptadoArticulos = new AdaptadoArticulos[c.getCount()];
            do{
                adaptadoArticulos[i] = new AdaptadoArticulos(c.getString(1),c.getString(2),Double.parseDouble(c.getString(3)),Double.parseDouble(c.getString(4)),c.getString(5),c.getString(6));
                System.out.println(c.getString(1)+"--"+c.getString(2)+"----"+c.getString(3)+"----"+c.getString(4));
                i++;
            }while (c.moveToNext());
        }
        ada = new AdaptadorArticulos(this, adaptadoArticulos);
        lista.setAdapter(ada);

        inputCodigo.addTextChangedListener(this);
        inputNombre.addTextChangedListener(this);
        setTitle("Articulos["+  String.valueOf(c.getCount())+"]");

    }

    public void filtro(String codigo,String nombre){
        codigo = codigo.toLowerCase();
        nombre = nombre.toLowerCase(Locale.getDefault());
        int count = 0;
        if (nombre.length() > 0 || codigo.length()>0 ) {
            for( AdaptadoArticulos aa:  adaptadoArticulos){
                if (  ( (aa.getInsu_codigo().toLowerCase().indexOf(codigo)!=-1)&&(codigo.length()>0) )
                        ||  ( (aa.getInsu_nombre().toLowerCase().indexOf(nombre)!=-1)&&(nombre.length()>0) )
                        )
                {
                    count++;
                }
            }
            AdaptadoArticulos[] aarticulo = new AdaptadoArticulos[count];
            int contador =0;
            for( AdaptadoArticulos aa:  adaptadoArticulos){
                if (   (aa.getInsu_codigo().toLowerCase().indexOf(codigo)!=-1)&&(codigo.length()>0)
                        ||  ( (aa.getInsu_nombre().toLowerCase().indexOf(nombre)!=-1)&&(nombre.length()>0) )
                        )
                {
                    aarticulo[contador] = aa;
                    contador++;
                }
            }

            ada = new AdaptadorArticulos(this,aarticulo);
            lista.setAdapter(ada);
            setTitle("Articulos["+  String.valueOf(count)+"]");
        }else{
            ada = new AdaptadorArticulos(this, adaptadoArticulos);
            lista.setAdapter(ada);
            setTitle("Articulos["+  String.valueOf( adaptadoArticulos.length)+"]");
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdaptadoArticulos item =  (AdaptadoArticulos) parent.getAdapter().getItem(position);
        Intent i = new Intent(this, AdicionarCantidad.class);
        bolsa.putString("insu_codigo",item.getInsu_codigo());
        bolsa.putString("insu_nombre",item.getInsu_nombre());
        bolsa.putString("insu_precio", String.valueOf(item.getInsu_precio()));
        bolsa.putString("insu_iva",String.valueOf(item.getInsu_iva()));
        bolsa.putString("insu_iva",String.valueOf(item.getInsu_iva()));
        bolsa.putString("insu_marca",item.getInsu_marca());
        i.putExtras(bolsa);
        startActivity(i);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        AdicionarArticulo.this.filtro(this.inputCodigo.getText().toString(), this.inputNombre.getText().toString());

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
