package com.example.proyecto

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlite.AdminSQLiteOpenHelper
import kotlinx.android.synthetic.main.activity_agregar.*

class Agregar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        editarBtn.setOnClickListener {
            try {
                if (nnombre.text.toString() != "" && ndireccion.text.toString() != "" && ntelefono.text.toString() != "" && ncorreo.text.toString() != "" && nurl.text.toString() != "") {
                    val admin = AdminSQLiteOpenHelper(this,"administracion", null, 1)
                    val bd = admin.writableDatabase
                    val registro = ContentValues()
                    registro.put("nombre", nnombre.getText().toString())
                    registro.put("direccion", ndireccion.getText().toString())
                    registro.put("telefono", ntelefono.getText().toString())
                    registro.put("correo", ncorreo.getText().toString())
                    registro.put("foto", nurl.getText().toString())
                    bd.insert("clientes", null, registro)
                    bd.close()
                    nnombre.setText("")
                    ndireccion.setText("")
                    ntelefono.setText("")
                    ncorreo.setText("")
                    nurl.setText("")
                } else {
                    Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                Toast.makeText(this, "Se guardo el articulo correctamente", Toast.LENGTH_SHORT).show()
                val intento3 = Intent(this, MainActivity::class.java)
                startActivity(intento3)
            }
        }
    }
}