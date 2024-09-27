package com.ibero.sqlita

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

class DataManager(context : Context) {
    val dbHelper = DBHelper(context)
    var db = dbHelper.writableDatabase

    fun abrir(){
        db = dbHelper.writableDatabase
    }

    fun cerrar(){
        db.close()
    }

    fun borrar(){
        dbHelper.onUpgrade(db, 1, 1)
    }

    fun guardarAlumno(alumno : Alumno){
        val valores = ContentValues(5);
        valores.put("nombre", alumno.nombre)
        valores.put("apPaterno", alumno.apPaterno)
        valores.put("apMaterno", alumno.apMaterno)
        valores.put("nacimiento", alumno.leerFecha())
        valores.put("sexo", alumno.sexo.toString())

        db.insert("alumnos", null, valores)
    }

    fun leerAlumno() : ArrayList<Alumno>{
        val alumnos = ArrayList<Alumno>()
        val columnas = arrayOf("matricula","nombre","apPaterno", "apMaterno", "nacimiento", "sexo")
        val cursor = db.query("alumnos", columnas, null, null, null, null, "matricula")

        while(cursor.moveToNext()){
            var alumno = Alumno()

            alumno.matricula = cursor.getInt(0)
            alumno.nombre = cursor.getString(1)
            alumno.apPaterno = cursor.getString(2)
            alumno.apMaterno = cursor.getString(3)
            alumno.setNacimiento(cursor.getString(4))
            alumno.sexo = cursor.getString(5).toCharArray().get(0)

            alumnos.add(alumno)
        }
        cursor.close()
        return alumnos
    }


    fun leerAlumno(matricula : Int) : Alumno{
        var alumno = Alumno()
        val columnas = arrayOf("matricula","nombre","apPaterno", "apMaterno", "nacimiento", "sexo")
        val cursor = db.query("alumnos", columnas, "matricula=?", null, null, null, "matricula")

        while(cursor.moveToNext()){
            alumno = Alumno()

            alumno.matricula = cursor.getInt(0)
            alumno.nombre = cursor.getString(1)
            alumno.apPaterno = cursor.getString(2)
            alumno.apMaterno = cursor.getString(3)
            alumno.setNacimiento(cursor.getString(4))
            alumno.sexo = cursor.getString(5).toCharArray().get(0)
        }
        cursor.close()
        return alumno
    }


}