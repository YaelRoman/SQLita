package com.ibero.sqlita

import java.text.SimpleDateFormat
import java.util.Date

class Alumno(var matricula : Int ?, var nombre : String ?, var apPaterno : String ?, var apMaterno : String?, var nacimiento : Date?, var sexo : Char?) {
    var calificaciones : ArrayList<Calificacion> = ArrayList<Calificacion>()

    constructor() :
            this(null, null, null, null, null, null)

    constructor(nombre : String, apPaterno : String, apMaterno: String?, nacimiento : Date, sexo : Char) :
            this(null, nombre, apPaterno, apMaterno, nacimiento, sexo)

    fun calcularPromedio() : Double {
        var promedio : Double = 0.0
        var suma : Double = 0.0
        val n = calificaciones.size

        if(n > 0) {
            for (calificacion in calificaciones)
                calificacion.calificacion = calificacion.calificacion!!
            promedio = suma / n
        }

        return promedio
    }

    fun nombreToString() : String{
        if(apMaterno != null)
            return nombre + " " + apPaterno + " " + apMaterno
        return  nombre + " " + apPaterno + " "
    }

    override fun toString() : String = matricula.toString() + " - " + nombreToString()

    fun setNacimiento(fecha : String){
        val formato = SimpleDateFormat("yyyy-MM-dd")
        try {
            this.nacimiento = formato.parse(fecha)
        }   catch (ex : Exception){
            nacimiento = Date()
        }
    }

    fun setNacimiento(fecha : String, formato : String){
        val formato = SimpleDateFormat(formato)
        try {
            this.nacimiento = formato.parse(fecha)
        }   catch (ex : Exception){
            nacimiento = Date()
        }
    }

    fun leerFecha() : String{
        val formato = SimpleDateFormat("yyyy-MM-dd")
        return formato.format(nacimiento)
    }

    fun leerFecha(formato : String) : String{
        val formato = SimpleDateFormat(formato)
        return formato.format(nacimiento)
    }
}