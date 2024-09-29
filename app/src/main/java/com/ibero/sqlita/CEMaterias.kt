package com.ibero.sqlita

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class CEMaterias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ce_materias)

        val dm = DataManager(this)

        val eTxtClave = findViewById<TextView>(R.id.eTxt_clave)
        val eTxtNombre = findViewById<TextView>(R.id.eTxt_nombre)
        val eTxtCreditos = findViewById<TextView>(R.id.eTxt_creditos)

        val btnGuardar = findViewById<Button>(R.id.btn_guardar)
        val btnActualizar = findViewById<Button>(R.id.btn_actualizar)
        val btnEliminar = findViewById<Button>(R.id.btn_eliminar)
        val btnLista = findViewById<Button>(R.id.btn_lista)

        btnGuardar.setOnClickListener(){
            var existe = false
            val materia = Materia()

            if(eTxtClave.text.isNotBlank()) {
                for(materia in dm.leerMaterias()){
                    if(materia.clave == eTxtClave.text.toString())
                        existe = true
                }

                if(!existe)
                    materia.clave = eTxtClave.text.toString()
            }

            if(eTxtNombre.text.isNotBlank())
                materia.nombre = eTxtNombre.text.toString()

            if(eTxtCreditos.text.isNotBlank())
                materia.creditos = eTxtCreditos.text.toString().toInt()

            if(existe)
                Toast.makeText(this, "Ya hay una materia registrada con esta clave", Toast.LENGTH_SHORT).show()
            else if(materia.clave == null || materia.nombre == null)
                Toast.makeText(this, "Debes ingresar los datos marcados como obligatorios", Toast.LENGTH_SHORT).show()
            else {
                dm.guardarMateria(materia)
                Toast.makeText(this, "Materia guardada", Toast.LENGTH_SHORT).show()
            }
        }

        btnActualizar.setOnClickListener(){
            val materia = Materia()

            if(eTxtClave.text.isNotBlank())
                materia.clave = eTxtClave.text.toString()

            if(eTxtNombre.text.isNotBlank())
                materia.nombre = eTxtNombre.text.toString()

            if(eTxtCreditos.text.isNotBlank())
                materia.creditos = eTxtCreditos.text.toString().toInt()

            if(materia.clave == null || materia.nombre == null || materia.creditos == null)
                Toast.makeText(this, "Debes ingresar los datos marcados como obligatorios", Toast.LENGTH_SHORT).show()
            else {
                dm.actualizarMateria(materia)
                Toast.makeText(this, "Materia actualizada", Toast.LENGTH_SHORT).show()
            }
        }

        btnEliminar.setOnClickListener(){
            val materia = Materia()

            if(eTxtClave.text.isNotBlank()){
                dm.eliminarMateria(eTxtClave.text.toString())
                Toast.makeText(this, "Materia eliminada", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Ingresa la clave de la materia a eliminar", Toast.LENGTH_SHORT).show()
            }
        }

        btnLista.setOnClickListener(){
            startActivity(Intent(this, CEListaMaterias::class.java))
        }
    }
}