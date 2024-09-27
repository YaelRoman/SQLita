package com.ibero.sqlita

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File

val DB_NAME = "escuelita_ibero.db"
val DB_VERSION = 1

class DBHelper(context : Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
       /* if (db != null) {
            db.execSQL("CREATE TABLE alumnos")
        }*/
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun getComando(n : Int) : String{
        val sql = File("comandos.sql").readText()
        val comandos = sql.split(";")

        return comandos.get(n)
    }
}