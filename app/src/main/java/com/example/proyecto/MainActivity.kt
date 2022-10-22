package com.example.proyecto

import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlite.AdminSQLiteOpenHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        agregarBtn.setOnClickListener {
            val intento1 = Intent(this, Agregar::class.java)
            startActivity(intento1)
        }

        listView.setOnItemClickListener{adapterView, view, position, id ->
            val idDb = adapterView.getItemAtPosition(position)
            val intento2 = Intent(this, Editar::class.java)
            intento2.putExtra("id", idDb.toString().toInt())
            startActivity(intento2)
        }
        cargarDatos()
    }

    private fun cargarDatos(){
        var id = arrayOf<Int>()
        var nombres = arrayOf<String>()
        var direcciones = arrayOf<String>()
        var imagenes = arrayOf<String>()
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.writableDatabase
        val data = bd.rawQuery("select * from clientes", null)
        while (data.moveToNext()) {
            id += data.getInt(0)
            nombres += data.getString(1)
            direcciones += data.getString(2)
            imagenes += data.getString(5)
        }
        bd.close()
        val myListAdapter = MyListAdapter(this,nombres, direcciones, imagenes, id)
        listView.adapter = myListAdapter
    }
}