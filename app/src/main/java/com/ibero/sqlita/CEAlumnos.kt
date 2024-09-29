package com.ibero.sqlita

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class CEAlumnos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ce_alumnos)

        val dm = DataManager(this)

        val txtVNombre = findViewById<TextView>(R.id.eTxt_nombre)
        val txtVApPaterno = findViewById<TextView>(R.id.eTxt_apPaterno)
        val txtVApMaterno = findViewById<TextView>(R.id.eTxt_apMaterno)
        val txtVNacimiento = findViewById<TextView>(R.id.eTxt_nacimiento)

        val spSexo = findViewById<Spinner>(R.id.sp_sexo)
        val opciones = listOf('F','M')
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        var seleccion : Char = 'F'

        val btnGuardar = findViewById<Button>(R.id.btn_guardar)
        val btnLista = findViewById<Button>(R.id.btn_lista)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSexo.adapter = adapter

        spSexo.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                seleccion = opciones[posicion]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //DO NOTHING, O.o
            }

        }

        btnGuardar.setOnClickListener(){
            val alumno = Alumno()

            if(txtVNombre.text.isNotBlank())
                alumno.nombre = txtVNombre.text.toString()

            if(txtVApPaterno.text.isNotBlank())
                alumno.apPaterno = txtVApPaterno.text.toString()

            if(txtVApMaterno.text.isNotBlank())
                alumno.apMaterno = txtVApMaterno.text.toString()

            if(txtVNacimiento.text.isNotBlank())
                alumno.setNacimiento(txtVNacimiento.text.toString())

            alumno.sexo = seleccion

            if(alumno.nombre == null || alumno.apPaterno == null || alumno.nacimiento == null)
                Toast.makeText(this, "Debes ingresar los datos marcados como obligatorios", Toast.LENGTH_SHORT).show()
            else{
                dm.guardarAlumno(alumno)
                Toast.makeText(this, "Alumno guardado", Toast.LENGTH_SHORT).show()
            }

        }

        btnLista.setOnClickListener(){
            startActivity(Intent(this, CEListaAlumnos::class.java))
        }
    }
}