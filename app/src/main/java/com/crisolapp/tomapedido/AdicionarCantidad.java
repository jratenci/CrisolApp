package com.crisolapp.tomapedido;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crisolapp.DB.modeloClientes;
import com.crisolapp.DB.modeloPedidos;
import com.crisolapp.R;
import com.crisolapp.librerias.MyActividad;

import java.text.NumberFormat;

public class AdicionarCantidad extends MyActividad implements TextWatcher,View.OnClickListener {

    TextView textCodigo,textNombre,textPrecio,textSubtotal;
    EditText editCantidad;
    NumberFormat formato;
    modeloClientes modeloclientes;

    Button btnCancelar, btnGuardar;

    Bundle bolsa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tomapedido_activity_adicionar_cantidad);
        bolsa = getIntent().getExtras();

        textCodigo   = (TextView)findViewById(R.id.txtAdicionarCodigo);
        textNombre   = (TextView)findViewById(R.id.txtAdicionarNombre);
        textPrecio   = (TextView)findViewById(R.id.txtAdicionarPrecio);
        textSubtotal = (TextView)findViewById(R.id.txtAdcionarSubtotal);
        editCantidad = (EditText)findViewById(R.id.editAdicionarCantidad);

        btnCancelar = (Button)findViewById(R.id.buttonCancelar);
        btnGuardar  = (Button)findViewById(R.id.buttonGuardar);

        btnCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

        textCodigo.setText(bolsa.getString("insu_codigo"));
        textNombre.setText(bolsa.getString("insu_nombre"));

        formato = NumberFormat.getCurrencyInstance();
        textPrecio.setText( formato.format( Double.parseDouble(bolsa.getString("insu_precio")) ));
        editCantidad.addTextChangedListener(this);

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String Stringcantidad = editCantidad.getText().toString();
        NumberFormat formato = NumberFormat.getCurrencyInstance();
        double subtotal = 0;
        int    cantidad = 0;
        try {
            cantidad =  Integer.parseInt(Stringcantidad);
            subtotal = Double.parseDouble(bolsa.getString("insu_precio")) * cantidad;
            textSubtotal.setText( formato.format( subtotal )  );
        } catch (NumberFormatException nfe){
            this.textSubtotal.setText("");
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonCancelar:
                Intent i = new Intent(this, TomaPedido.class);
                i.putExtras(bolsa);
                startActivity(i);
            break;
            case R.id.buttonGuardar:
                String Stringcantidad = editCantidad.getText().toString();
                int    cantidad = 0;
                try {
                    cantidad =  Integer.parseInt(Stringcantidad);
                    this.guardarArticulo(cantidad);
                    Intent ii = new Intent(this, TomaPedido.class);
                    ii.putExtras(bolsa);

                    ContentValues content = new ContentValues();
                    content.put("clie_visitado","S");
                    modeloclientes = new modeloClientes(this);
                    modeloclientes.Modificar(content,"clie_codigo",bolsa.getString("clie_codigo") );
                    startActivity(ii);
                } catch (NumberFormatException nfe){
                 nfe.printStackTrace();
                }
                break;
        }

    }

    private void guardarArticulo(int cantidad ){
        double precioSiniva, precioConiva,iva;

        precioConiva = Double.parseDouble(bolsa.getString("insu_precio"));
        iva = Double.parseDouble(bolsa.getString("insu_iva"));
        precioSiniva = precioConiva/(1+ (iva/100)  );
        modeloPedidos mp = new modeloPedidos(this);
        ContentValues pedido = new ContentValues();
        pedido.put("pedi_insumo",bolsa.getString("insu_codigo"));
        pedido.put("pedi_cantidad",cantidad);
        pedido.put("pedi_precio",precioSiniva);
        pedido.put("pedi_vendedor",bolsa.getString("usua_codigo"));
        pedido.put("pedi_cliente",bolsa.getString("clie_codigo"));
        pedido.put("pedi_estado","A");
        pedido.put("pedi_comentario","");
        pedido.put("pedi_precioiva",precioConiva);
        pedido.put("pedi_marca",bolsa.getString("insu_marca"));
        pedido.put("pedi_nombreinsumo",bolsa.getString("insu_nombre"));
        pedido.put("pedi_insu_iva",bolsa.getString("insu_iva"));

        mp.InsertarTabla(pedido);


    }
}
