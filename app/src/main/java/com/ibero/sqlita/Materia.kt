package com.ibero.sqlita

class Materia (var clave : String?, var nombre : String?, var creditos: Int?){

    constructor() : this(null, null, 10)

    override fun toString(): String = clave + " - " + nombre + " - " + creditos
}