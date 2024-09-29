package com.ibero.sqlita

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import java.util.Date

class Maestros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_maestros)

        val dm = DataManager(this)

        val spMateria = findViewById<Spinner>(R.id.sp_materia)
        var opcionesMateria = ArrayList<String>()
        val listaMaterias = dm.leerMaterias()
        var seleccionMateria : Materia? = null

        val spAlumno = findViewById<Spinner>(R.id.sp_alumno)
        var opcionesAlumno = ArrayList<String>()
        val listaAlumnos = dm.leerAlumnos()
        var seleccionAlumno : Alumno? = null

        val eTxtCalificacion = findViewById<EditText>(R.id.eTxt_calificacion)

        val btnGuardar = findViewById<Button>(R.id.btn_guardar)

        for(materia in listaMaterias)
            opcionesMateria.add(materia.toString())

        spMateria.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesMateria)

        spMateria.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                seleccionMateria = listaMaterias.get(posicion)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        for(alumno in dm.leerAlumnos())
            opcionesAlumno.add(alumno.toString())

        spAlumno.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesAlumno)

        spAlumno.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                seleccionAlumno = listaAlumnos.get(posicion)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        btnGuardar.setOnClickListener(){
            val calificacion = Calificacion()

            if(eTxtCalificacion.text.isNotEmpty()){
                calificacion.materia = seleccionMateria
                calificacion.setFecha()
                calificacion.calificacion = eTxtCalificacion.text.toString().toDouble()

                dm.guardarCalificacion(seleccionAlumno!!.matricula!!.toInt(), calificacion)
                Toast.makeText(this, "Calificación guardada", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Ingresa la calificación a asignar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}