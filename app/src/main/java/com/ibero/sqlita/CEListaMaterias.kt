package com.ibero.sqlita

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CEListaMaterias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ce_lista_materias)

        val dm = DataManager(this)
        val materias = dm.leerMaterias()
        val lvMaterias = findViewById<ListView>(R.id.lV_materias)
        val lvElementos = ArrayList<String>()

        for(materia in materias)
            lvElementos.add(materia.toString())

        lvMaterias.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lvElementos)
    }
}