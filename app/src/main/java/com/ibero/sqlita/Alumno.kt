package com.ibero.sqlita

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date

class Alumno(var matricula : Int ?, var nombre : String ?, var apPaterno : String ?, var apMaterno : String?, var nacimiento : Date?, var sexo : Char?) {
    lateinit var calificaciones : ArrayList<Calificacion>

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
                suma += calificacion.calificacion
            promedio = suma / n
        }

        return promedio
    }

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

    override fun toString(): String {
        return "Alumno(matricula=$matricula, nombre='$nombre', apPaterno='$apPaterno', apMaterno='$apMaterno', nacimiento=$nacimiento, sexo=$sexo)"
    }
}