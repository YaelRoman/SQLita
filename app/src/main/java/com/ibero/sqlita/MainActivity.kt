package com.ibero.sqlita

import android.os.Bundle
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView : TextView = findViewById(R.id.textView)
        val db = DBHelper(this)
        textView.setText(db.getComando(0)+"-"+db.getComando(1)+"-"+db.getComando(2))
            val dataManager = DataManager(this)
        //dataManager.guardarAlumno(Alumno(123,"Monica", "Medrano", "Valle", Date(), 'F'))

    }
}