package com.reservando.proyectoferreteria;

import android.provider.BaseColumns;

/**
 * Created by KARINA on 01/12/2015.
 */
public class EstructuraDatos {
    public EstructuraDatos(){}

    public static abstract class Estructura implements BaseColumns
    {

        public static final String PROYECT_NAME = "FERRETERIA";

        public static final String TABLE_LOGIN_NAME = "LOGIN";

        public static final String TABLE_PRODUCTO_NAME = "PRODUCTO";
        public static final String COLUMN_PRODUCTO_NAME_PRODUCTO = "PRODUCTO";
        public static final String COLUMN_PRODUCTO_NAME_SECCION = "SECCION";
        public static final String COLUMN_PRODUCTO_NAME_CANTIDAD = "CANTIDAD";
        public static final String COLUMN_PRODUCTO_NAME_PRECIO = "PRECIO";

        public static final String TABLE_CLIENTE_NAME = "CLIENTE";
        public static final String COLUMN_CLIENTE_NAME_NOMBRE = "NOMBRE";
        public static final String COLUMN_CLIENTE_NAME_APELLIDO = "APELLIDO";
        public static final String COLUMN_CLIENTE_NAME_GENERO = "GENERO";
        public static final String COLUMN_CLIENTE_NAME_FECHA = "FECHA";

        public static final String TABLE_VENTA_NAME = "VENTA";
        public static final String COLUMN_VENTA_NAME_CLIENTE = "CLIENTE";
        public static final String COLUMN_VENTA_NAME_PRODUCTO = "PRODUCTO";
        public static final String COLUMN_VENTA_NAME_CANTIDAD = "CANTIDAD";
        public static final String COLUMN_VENTA_NAME_PRECIO = "PRECIO";
    }
}
