package com.ibero.sqlita

class Materia (var clave : String?, var nombre : String?){
    var creditos : Int
        get() : Int = creditos
        set(creditos) {
            this.creditos = creditos
        }

    constructor() : this(null, null) {creditos = 10}

    constructor(clave : String, nombre : String, creditos : Int) :
        this(clave, nombre){
            this.creditos = creditos
        }

    override fun toString(): String = clave + " - " + nombre
}