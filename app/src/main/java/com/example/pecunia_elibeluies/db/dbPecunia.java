package com.example.pecunia_elibeluies.db;
import androidx.annotation.Nullable;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pecunia_elibeluies.UsuarioSingleton;

import java.util.Date;

public class dbPecunia extends dbHelper{

    private Context context;

    public dbPecunia(@Nullable Context context) {
        super(context);
        this.context = context;

    }
    //para usuario------------------------------------------------------------------------------------
    public long insertarUsuario( String nameR,String emailR, String passwordr){
        //Creamos un objeto para realizar la escritura de los datos que usaremos
        //creamos un objeto de la clase dbhelper y vamos a escribir en nuestra bd
        dbHelper DBHelper = new dbHelper(context);
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("UserName", nameR);
        values.put("Email", emailR);
        values.put("Password", passwordr);

        //ejecuta
        long id = db.insert(DB_TABLE_USUARIO, null, values);

        return id;
    }

    /*else {
        return -1; // Manejar el caso en el que no se pudo obtener la instancia de la base de datos
    }*/
    /* public long Ingresos()*/

    //metodo actualizar contraseña
    public long actualizarPassword(int folioUsuario, String password){

        dbHelper DBHelper = new dbHelper(context);
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        //mandamos valor nuevo
        ContentValues valor = new ContentValues();
        valor.put("Password", password);

        //ejecuta
        long id = db.update(DB_TABLE_USUARIO, valor, "FolioUsuario = " + folioUsuario, null);

        return id;
    }

    //metodo actualizar nombre
    public long actualizarNombre(int folioUsuario, String userName){

        dbHelper DBHelper = new dbHelper(context);
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        //mandamos valor nuevo
        ContentValues valor = new ContentValues();
        valor.put("UserName", userName);

        //ejecuta
        long id = db.update(DB_TABLE_USUARIO, valor, "FolioUsuario = " + folioUsuario, null);

        return id;
    }

    //para meta------------------------------------------------------
    //metodo insertar en meta
    public long insertarMeta(Date fechaInicio, Date fechaLimite, double cantidadRecaudada, double cantidadAlcanzar,
                             String concepto, int folioUsuario){

        dbHelper DBHelper = new dbHelper(context);
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("FechaInicio", fechaInicio.getTime());
        values.put("FechaLimite", String.valueOf(fechaLimite));
        values.put("CantidadRecaudada", cantidadRecaudada);
        values.put("CantidadAlcanzar", cantidadAlcanzar);
        values.put("Concepto", concepto);
        values.put("FolioUsuario", folioUsuario);

        //ejecuta
        long id = db.insert(DB_TABLE_META, null, values);

        return id;
    }

    //para ingresos--------------------------------------------------
    public long insertarIngreso(String concepto, String tipo, Date fechaIngreso, String periodo,
                                double cantidad, int folioUsuario){

        dbHelper DBHelper = new dbHelper(context);
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Concepto", concepto);
        values.put("Tipo", tipo);
        values.put("FechaIngreso", fechaIngreso.getTime());
        values.put("Periodo", periodo);
        values.put("Cantidad", cantidad);
        values.put("FolioUsuario", folioUsuario);

        //ejecuta
        long id = db.insert(DB_TABLE_INGRESOS, null, values);

        return id;
    }

    //para egresos---------------------------------------------------
    public long insertarEgreso(String concepto, String tipo, Date fechaEgreso, String periodo,
                               double cantidad, int folioUsuario){

        dbHelper DBHelper = new dbHelper(context);
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Concepto", concepto);
        values.put("Tipo", tipo);
        values.put("Fecha", fechaEgreso.getTime());
        values.put("Periodo", periodo);
        values.put("Cantidad", cantidad);
        values.put("FolioUsuario", folioUsuario);

        //ejecuta
        long id = db.insert(DB_TABLE_EGRESOS, null, values);

        return id;
    }
    public long insertarIngresoV(String concepto, String tipo, Date fechaIngreso,
                                double cantidad, int folioUsuario){

        dbHelper DBHelper = new dbHelper(context);
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Concepto", concepto);
        values.put("Tipo", tipo);
        values.put("FechaIngreso", fechaIngreso.getTime());
        values.put("Cantidad", cantidad);
        values.put("FolioUsuario", folioUsuario);

        //ejecuta
        long id = db.insert(DB_TABLE_INGRESOS, null, values);

        return id;
    }

    //para egresos---------------------------------------------------
    public long insertarEgresoV(String concepto, String tipo, Date fechaEgreso,
                               double cantidad, int folioUsuario){

        dbHelper DBHelper = new dbHelper(context);
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Concepto", concepto);
        values.put("Tipo", tipo);
        values.put("Fecha", fechaEgreso.getTime());
        values.put("Cantidad", cantidad);
        values.put("FolioUsuario", folioUsuario);

        //ejecuta
        long id = db.insert(DB_TABLE_EGRESOS, null, values);

        return id;
    }
    public boolean verificarCredenciales(String nombreUsuario, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
       // int folioUsuario = 1;
        // Define la consulta SQL
        String consulta = "SELECT * FROM " + DB_TABLE_USUARIO + " WHERE UserName = ? AND Password = ?";

        // Ejecuta la consulta con dos parámetros en lugar de
        Cursor cursor = db.rawQuery(consulta, new String[]{nombreUsuario, password});

        //Obtiene el folio
        //UsuarioSingleton.getInstance().setFolioUsuario(folioUsuario);

        // Verifica si se encontraron resultados
        boolean credencialesCorrectas = cursor.moveToFirst();

        // Cierra el cursor y la base de datos
        cursor.close();
        db.close();

        return credencialesCorrectas;
    }

    @SuppressLint("Range")
    public int obtenerFolioo(String nombre) {

        SQLiteDatabase db = this.getReadableDatabase();

        // Añadí un espacio después del nombre de la tabla
        String consulta = "SELECT FolioUsuario FROM " + DB_TABLE_USUARIO + " WHERE UserName = ?";

        // Puedes ajustar esta consulta según la estructura de tu base de datos
        Cursor cursor = db.rawQuery(consulta, new String[]{nombre});

        // Valor predeterminado para folio
        int folioUsuario = -1;

        if (cursor.moveToFirst())
            folioUsuario = cursor.getInt(cursor.getColumnIndex("FolioUsuario"));


        UsuarioSingleton.getInstance().setFolioUsuario(folioUsuario);
        cursor.close();
        db.close();

        return folioUsuario;
    }



    /*public boolean verificarCredenciales(String nombreUsuario, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM tabla WHERE UserName = " + nombreUsuario + " AND Password = " + password ;
        Cursor cursor = db. rawQuery(query, null);

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }*/
}