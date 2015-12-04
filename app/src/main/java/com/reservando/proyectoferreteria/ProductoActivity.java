package com.reservando.proyectoferreteria;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.reservando.proyectoferreteria.EstructuraDatos.Estructura;
import android.database.Cursor;


public class ProductoActivity extends AppCompatActivity {
    private EditText edProducto;
    private EditText edCantidad;
    private EditText edId;
    private Spinner spnSeccion;
    private EditText edPrecio;

    BaseDatosProducto basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        edId = (EditText)findViewById(R.id.edIdentificador);
        edProducto = (EditText)findViewById(R.id.edProducto);
        spnSeccion = (Spinner)findViewById(R.id.spinnerSeccion);
        edCantidad = (EditText)findViewById(R.id.edCantidad);
        edPrecio = (EditText)findViewById(R.id.edPrecio);


        String[] secciones = {"Electricidad","Fontanería","Cerrajería","Jardinería","Tornillería","Vestimenta"};
        spnSeccion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, secciones));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_producto, menu);
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

    public void guardarProducto(View view)
    {

        basedatos = new BaseDatosProducto(this);

        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        String producto = edProducto.getText().toString();
        String seccion = spnSeccion.getSelectedItem().toString();
        String cantidad = edCantidad.getText().toString();
        String precio = edCantidad.getText().toString();

        ContentValues content = new ContentValues();

        if(producto.equals("") || cantidad.equals(""))
        {
            Toast.makeText(this, "Revise los datos introducidos. Todos los campos son obligatorios.", 3000).show();
        }else
        {

            content.put(Estructura.COLUMN_PRODUCTO_NAME_PRODUCTO,producto);
            content.put(Estructura.COLUMN_PRODUCTO_NAME_SECCION, seccion);
            content.put(Estructura.COLUMN_PRODUCTO_NAME_CANTIDAD, cantidad);
            content.put(Estructura.COLUMN_PRODUCTO_NAME_PRECIO, precio);
            sqlite.insert(Estructura.TABLE_PRODUCTO_NAME, null, content);
            Toast.makeText(this, "ProductoActivity " + producto + " ha sido almacenado.", 3000).show();
            edId.setText("");
            edProducto.setText("");
            edCantidad.setText("");
            edPrecio.setText("");
        }

        sqlite.close();
    }


    public void buscarProducto(View view)
    {

        basedatos = new BaseDatosProducto(this);

        SQLiteDatabase sqlite = basedatos.getReadableDatabase();

        String[] columnas = {
                Estructura._ID,
                Estructura.COLUMN_PRODUCTO_NAME_PRODUCTO,
                Estructura.COLUMN_PRODUCTO_NAME_CANTIDAD,
                Estructura.COLUMN_PRODUCTO_NAME_SECCION
        };

        //Cláusula WHERE para buscar por producto
        String producto = Estructura.COLUMN_PRODUCTO_NAME_PRODUCTO + " LIKE '" +  edProducto.getText().toString() + "'";
        //Orden de los resultados devueltos por ProductoActivity, de forma Descendente alfabéticamente
        String ordenSalida = Estructura.COLUMN_PRODUCTO_NAME_PRODUCTO + " DESC";

        if(producto.equals(""))
        {
            Toast.makeText(this, "Debe indicar el producto a buscar en la base de datos.", 3000).show();
        }else
        {

            Cursor cursor = sqlite.query(Estructura.TABLE_PRODUCTO_NAME, columnas, producto,null , null, null, ordenSalida);

            if(cursor.getCount() != 0)
            {
                cursor.moveToFirst();

                long identificador = cursor.getLong(cursor.getColumnIndex(Estructura._ID));
                Toast.makeText(this, "El ProductoActivity " +  edProducto.getText().toString()
                        + " está almacenado con Identificador " + identificador, 3000).show();
                edProducto.setText("");
                edCantidad.setText("");
                edId.setText("");

            }
            else
            {
                Toast.makeText(this, "El ProductoActivity '" + edProducto.getText().toString()  + "' no existe en la Base de Datos.", 3000).show();
            }

        }
        //Se cierra la conexión abierta a la Base de Datos
        sqlite.close();

    }

    //Evento On Click para eliminar un producto de la tabla Ventas por el nombre
    public void borrarProducto(View view)
    {

        String producto_eliminar = edProducto.getText().toString();
        //Se inicializa la clase.
        basedatos = new BaseDatosProducto(this);
        //Se establecen permisos de escritura
        SQLiteDatabase sqlite = basedatos.getWritableDatabase();
        if(producto_eliminar.equals(""))
        {
            Toast.makeText(this, "Debe indicar el producto a eliminar de la base de datos.", 3000).show();
        }else
        {
            //Se especifica el campo ProductoActivity y el producto introducido en el campo de texto a eliminar
            String consulta = Estructura.COLUMN_PRODUCTO_NAME_PRODUCTO + " LIKE '" + producto_eliminar + "'";
            //Se borra el producto indicado en el campo de texto
            sqlite.delete(Estructura.TABLE_PRODUCTO_NAME, consulta, null);
            Toast.makeText(this, "Se ha eliminado el producto: " + producto_eliminar, 3000).show();
            edProducto.setText("");
            edCantidad.setText("");
            edId.setText("");

        }
        //Se cierra la conexión abierta a la Base de Datos
        sqlite.close();
    }


    public void modificarProducto(View view)
    {

        basedatos = new BaseDatosProducto(this);


        SQLiteDatabase sqlite = basedatos.getWritableDatabase();

        Long identificador = Long.valueOf(edId.getText().toString());
        String producto_modificar = edProducto.getText().toString();
        String cantidad_modificar = edCantidad.getText().toString();
        String seccion_modificar = spnSeccion.getSelectedItem().toString();

        ContentValues content = new ContentValues();

        content.put(Estructura.COLUMN_PRODUCTO_NAME_PRODUCTO, producto_modificar);
        content.put(Estructura.COLUMN_PRODUCTO_NAME_CANTIDAD, cantidad_modificar);
        content.put(Estructura.COLUMN_PRODUCTO_NAME_SECCION, seccion_modificar);
        if(producto_modificar.equals("") || cantidad_modificar.equals(""))
        {
            Toast.makeText(this, "Revise los datos introducidos. Todos los campos son obligatorios.", 3000).show();
        }else
        {
            String selection = Estructura._ID + " LIKE " + identificador;

            int count = sqlite.update(
                    Estructura.TABLE_PRODUCTO_NAME,
                    content,
                    selection,
                    null);
            Toast.makeText(this, "Se ha actualizado el producto: " + producto_modificar +
                    ". Registros modificados: " + count, 3000).show();
            edProducto.setText("");
            edCantidad.setText("");
            edId.setText("");
        }

        sqlite.close();
    }
}
