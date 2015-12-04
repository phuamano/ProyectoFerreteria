package com.reservando.proyectoferreteria;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ClienteActivity extends AppCompatActivity {

    ClienteDataBaseAdapter clienteDataBaseAdapter;

    private EditText edId;
    private EditText edNombre;
    private EditText edApellido;
    private Spinner  spnSeccion;
    private EditText edFecha;
    Button btnGuardar;
    //BaseDatosProducto basedatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        edId = (EditText)findViewById(R.id.edIdentificador);
        edNombre = (EditText)findViewById(R.id.edNombre);
        edApellido = (EditText)findViewById(R.id.edApellido);
        spnSeccion = (Spinner)findViewById(R.id.spinnerSeccion);
        edFecha = (EditText)findViewById(R.id.edFecha);


        String[] secciones = {"Femenino","Masculino"};
        spnSeccion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, secciones));

        btnGuardar=(Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String nombre =edNombre.getText().toString();
                String apellido=edApellido.getText().toString();
                String seccion =spnSeccion.getSelectedItem().toString();
                String fecha=edFecha.getText().toString();

                clienteDataBaseAdapter.insertEntry(nombre, apellido,seccion,fecha);
                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

 /*   public void GGguardarProducto(View view)
    {

        basedatos = new BaseDatosProducto(this);

        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        String nombre = edNombre.getText().toString();
        String apellido = edApellido.getText().toString();
        String seccion = spnSeccion.getSelectedItem().toString();
        String fecha = edFecha.getText().toString();

        ContentValues content = new ContentValues();

        if(nombre.equals("") || apellido.equals(""))
        {
            Toast.makeText(this, "Revise los datos introducidos. Todos los campos son obligatorios.", 3000).show();
        }else
        {

            content.put(EstructuraDatos.Estructura.COLUMN_CLIENTE_NAME_NOMBRE,nombre);
            content.put(EstructuraDatos.Estructura.COLUMN_CLIENTE_NAME_APELLIDO, apellido);
            content.put(EstructuraDatos.Estructura.COLUMN_CLIENTE_NAME_GENERO, seccion);
            content.put(EstructuraDatos.Estructura.COLUMN_CLIENTE_NAME_FECHA, fecha);
            sqlite.insert(EstructuraDatos.Estructura.TABLE_CLIENTE_NAME, null, content);
            Toast.makeText(this, "ClienteActivity " + nombre + " ha sido almacenado.", 3000).show();
            edId.setText("");
            edNombre.setText("");
            edApellido.setText("");
            edFecha.setText("");
        }

        sqlite.close();
    }*/
}
