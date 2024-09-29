package com.ibero.sqlita

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ControlEscolar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_control_escolar)

        val btnMaterias = findViewById<Button>(R.id.btn_materias)
        val btnAlumnos = findViewById<Button>(R.id.btn_alumnos)

        btnMaterias.setOnClickListener(){
            startActivity(Intent(this, CEMaterias::class.java))
        }

        btnAlumnos.setOnClickListener(){
            startActivity(Intent(this, CEAlumnos::class.java))
        }
    }
}