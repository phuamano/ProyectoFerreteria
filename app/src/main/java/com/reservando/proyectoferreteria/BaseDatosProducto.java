package com.reservando.proyectoferreteria;

import com.reservando.proyectoferreteria.EstructuraDatos.Estructura;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by KARINA on 01/12/2015.
 */
public class BaseDatosProducto  extends SQLiteOpenHelper {


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EstructuraDatos.Estructura.TABLE_PRODUCTO_NAME + " (" +
                    Estructura._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Estructura.COLUMN_PRODUCTO_NAME_PRODUCTO + TEXT_TYPE + COMMA_SEP +
                    Estructura.COLUMN_PRODUCTO_NAME_CANTIDAD + TEXT_TYPE + COMMA_SEP +
                    Estructura.COLUMN_PRODUCTO_NAME_SECCION + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura.TABLE_PRODUCTO_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ProductoActivity.sqlite";


    public BaseDatosProducto(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    //Método que elimina la tabla y vuelve a llamar al método que la crea
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
