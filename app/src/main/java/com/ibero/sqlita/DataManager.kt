package com.ibero.sqlita

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.ibero.sqlita.DBHelper.Companion.getComando

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

    fun leerAlumnos() : ArrayList<Alumno>{
        val alumnos = ArrayList<Alumno>()
        val columnas = arrayOf("matricula","nombre","apPaterno", "apMaterno", "nacimiento", "sexo")
        val cursor = db.query("alumnos", columnas, null, null,
                      null, null, "matricula")

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
        val cursor = db.query("alumnos", columnas, "matricula=?",null,
                      null, null, "matricula")

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

    fun guardarMateria(materia : Materia) : Long {
        val valores = ContentValues(3)

        valores.put("clave", materia.clave)
        valores.put("nombre", materia.nombre)
        valores.put("creditos", materia.creditos)

        return db.insert("materias", null, valores)
    }

    fun actualizarMateria(materia : Materia) : Int {
        val valores = ContentValues(2)
        val argumentos = arrayOf(materia.clave)

        valores.put("nombre", materia.nombre)
        valores.put("creditos", materia.creditos)

        return db.update("materias", valores, "clave=?", argumentos)
    }

    fun eliminarMateria(materia : Materia) : Int {
        val argumentos = arrayOf(materia.clave)

        return db.delete("materias", "clave:?", argumentos)
    }

    fun eliminarMateria(clave : String) : Int {
        val argumentos = arrayOf(clave)

        return db.delete("materias", "clave=?", argumentos)
    }

    fun leerMaterias() : ArrayList<Materia>{
        val materias = ArrayList<Materia>()
        val columnas = arrayOf("clave", "nombre", "creditos")
        val cursor = db.query("materias", columnas, null, null,
                      null, null, "clave")

        while(cursor.moveToNext()){
            val materia = Materia()

            materia.clave = cursor.getString(0)
            materia.nombre = cursor.getString(1)
            materia.creditos = cursor.getInt(2)

            materias.add(materia)
        }

        cursor.close()
        return materias
    }

    fun guardarCalificacion(matricula : Int, calificacion : Calificacion) : Long {
        val valores = ContentValues(4)

        valores.put("alumno", matricula)
        valores.put("materia", calificacion.materia!!.clave)
        valores.put("calificacion", calificacion.calificacion)
        valores.put("fecha", calificacion.leerFecha())

        return db.insert("calificaciones", null, valores)
    }

    fun actualizarCalificacion(matricula : Int, calificacion : Calificacion) : Int {
        val valores = ContentValues(4)
        val argumentos = arrayOf(matricula.toString(), calificacion.materia!!.clave)

        valores.put("calificacion", calificacion.calificacion)
        valores.put("fecha", calificacion.leerFecha())

        return db.update("calificaciones", valores, "alumno=? AND materia=?", argumentos)
    }

    fun leerCalificaciones(matricula : Int) : ArrayList<Calificacion>{
        val calificaciones = ArrayList<Calificacion>()
        val argumentos = arrayOf(matricula.toString())
        val cursor = db.rawQuery(getComando(6), argumentos)

        while(cursor.moveToNext()){
            val materia = Materia()
            val calificacion = Calificacion()

            materia.clave = cursor.getString(0)
            materia.nombre = cursor.getString(3)
            materia.creditos = cursor.getInt(4)

            calificacion.materia = materia
            calificacion.calificacion = cursor.getDouble(1)
            calificacion.setFecha(cursor.getString(2))

            calificaciones.add(calificacion)
        }

        cursor.close()
        return calificaciones
    }

    fun leerCalificacion(matricula : Int, claveMateria : String) : Calificacion? {
        var calificacion : Calificacion? = null
        val argumentos = arrayOf(claveMateria, matricula.toString())
        val cursor = db.rawQuery(getComando(7), argumentos)

        while(cursor.moveToNext()){
            val materia = Materia()
            calificacion = Calificacion()

            materia.clave = cursor.getString(0)
            materia.nombre = cursor.getString(3)
            materia.creditos = cursor.getInt(4)

            calificacion.calificacion = cursor.getDouble(1)
            calificacion.setFecha(cursor.getString(2))
            calificacion.materia = materia
        }

        cursor.close()
        return calificacion
    }
}