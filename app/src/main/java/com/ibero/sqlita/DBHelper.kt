package com.ibero.sqlita

import android.content.Context
import android.content.res.Resources
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File

val DB_NAME = "escuelita_ibero.db"
val DB_VERSION = 1

class DBHelper(context : Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
       if (db != null){
           db.execSQL(getComando(0))
           db.execSQL(getComando(1))
           db.execSQL(getComando(2))
       }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL(getComando(3))
            db.execSQL(getComando(4))
            db.execSQL(getComando(5))
        }
    }

    fun getComando(n : Int) : String{
        val sql = this::class.java.classLoader.getResource("res/raw/comandos.txt").readText()

        val comandos = sql.split(";")
        return comandos.get(n)
    }
}