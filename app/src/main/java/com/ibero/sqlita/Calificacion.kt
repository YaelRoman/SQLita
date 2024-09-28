package com.ibero.sqlita

import java.text.SimpleDateFormat
import java.util.Date

class Calificacion(var materia : Materia?, var calificacion : Double?) {
    var fecha : Date = Date()

    constructor() : this(null, null)

    override fun toString() : String = materia.toString() + " : " + String.format("%1$,.2f", calificacion)

    fun setFecha(fecha : String, formato : String){
        val formato = SimpleDateFormat(formato)
        try {
            this.fecha = formato.parse(fecha)
        }   catch (ex : Exception){
            this.fecha = Date()
        }
    }

    fun setFecha(fecha : String){
        val formato = SimpleDateFormat("yyyy-MM-dd")
        try {
            this.fecha = formato.parse(fecha)
        }   catch (ex : Exception){
            this.fecha = Date()
        }
    }

    fun leerFecha() : String{
        val formato = SimpleDateFormat("yyyy-MM-dd")
        return formato.format(fecha)
    }
}