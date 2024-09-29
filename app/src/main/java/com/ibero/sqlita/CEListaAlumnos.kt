package com.ibero.sqlita

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CEListaAlumnos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ce_lista_alumnos)

        val dm = DataManager(this)
        val alumnos = dm.leerAlumnos()
        val lvAlumnos = findViewById<ListView>(R.id.lV_alumnos)
        val lvElementos = ArrayList<String>()

        for(alumno in alumnos)
            lvElementos.add(alumno.toString())

        lvAlumnos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lvElementos)
    }
}