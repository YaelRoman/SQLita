package com.ibero.sqlita

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Alumnos : AppCompatActivity() {
    lateinit var dm : DataManager
    lateinit var eTxtMatricula : EditText
    lateinit var txtVNombre : TextView
    lateinit var txtVSexo : TextView
    lateinit var txtVNacimiento : TextView
    lateinit var txtVPromedio : TextView

    lateinit var lVCalificaciones : ListView
    lateinit var calificaciones : ArrayList<Calificacion>

    var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if(eTxtMatricula.text.isNotBlank()){
                val alumno = dm.leerAlumno(eTxtMatricula.text.toString().toInt())
                var promedio = 0.0
                var totalCalificaciones = 0
                val lvElementos = ArrayList<String>()


                if(alumno.matricula != null){
                    txtVNombre.text = "Nombre: " + alumno.nombreToString()
                    txtVSexo.text = "Sexo: " + alumno.sexo.toString()
                    txtVNacimiento.text ="Fecha de nacimiento: " + alumno.leerFecha()

                    calificaciones = dm.leerCalificaciones(alumno.matricula!!.toInt())

                    for (calificacion in calificaciones){
                        promedio += calificacion.calificacion!!
                        totalCalificaciones++
                        lvElementos.add(calificacion.toString())
                    }

                    lVCalificaciones.adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, lvElementos)

                    promedio /= totalCalificaciones
                    txtVPromedio.text = "Promedio: " + promedio.toString()
                }
                else{
                    txtVNombre.text = "No hay datos para esta matrícula"
                    txtVSexo.text = ""
                    txtVNacimiento.text = ""
                    txtVPromedio.text = ""
                }
            }
        }

        override fun afterTextChanged(s: Editable) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alumnos)

        dm = DataManager(this)

        eTxtMatricula = findViewById<EditText>(R.id.eTxt_matricula)
        txtVNombre = findViewById<TextView>(R.id.txtV_nombre)
        txtVSexo = findViewById<TextView>(R.id.txtV_sexo)
        txtVNacimiento = findViewById<TextView>(R.id.txtV_nacimiento)
        txtVPromedio = findViewById<TextView>(R.id.txtV_promedio)

        lVCalificaciones = findViewById<ListView>(R.id.lV_calificaciones)

        eTxtMatricula.addTextChangedListener(textWatcher)

        txtVNombre.text = "Ingresa una matrícula"
        txtVSexo.text = ""
        txtVNacimiento.text = ""
        txtVPromedio.text = ""

    }
}