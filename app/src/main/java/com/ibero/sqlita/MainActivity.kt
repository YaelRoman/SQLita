package com.ibero.sqlita

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnControlEscolar = findViewById<Button>(R.id.btn_controlEscolar)
        val btnMaestros = findViewById<Button>(R.id.btn_maestros)
        val btnAlumno = findViewById<Button>(R.id.btn_alumnos)

        btnControlEscolar.setOnClickListener(){
            startActivity(Intent(this, ControlEscolar::class.java ))
        }

        btnMaestros.setOnClickListener(){
            startActivity(Intent(this, Maestros::class.java))
        }

        btnAlumno.setOnClickListener(){
            startActivity(Intent(this, Alumnos::class.java))
        }
    }
}