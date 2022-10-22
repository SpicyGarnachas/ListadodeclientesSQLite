package com.example.proyecto

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlite.AdminSQLiteOpenHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_editar.*
import kotlinx.android.synthetic.main.activity_editar.editarBtn

class Editar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)
        val bundle = intent.extras
        val id = bundle?.getInt("id")

        try{
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select nombre,direccion,telefono,correo,foto from clientes where id=${id.toString()}", null)
            if (fila.moveToFirst()) {
                nombre.setText(fila.getString(0))
                direccion.setText(fila.getString(1))
                telefono.setText(fila.getString(2))
                correo.setText(fila.getString(3))
                url.setText(fila.getString(4))
                Picasso.get().load(fila.getString(4)).into(foto)

            }
            bd.close()
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }


        editarBtn.setOnClickListener {
            try {
                if (nombre.text.toString() != "" && direccion.text.toString() != "" && telefono.text.toString() != "" && correo.text.toString() != "" && url.text.toString() != "") {
                    val admin = AdminSQLiteOpenHelper(this,"administracion", null, 1)
                    val bd = admin.writableDatabase
                    val registro = ContentValues()
                    registro.put("nombre", nombre.getText().toString())
                    registro.put("direccion", direccion.getText().toString())
                    registro.put("telefono", telefono.getText().toString())
                    registro.put("correo", correo.getText().toString())
                    registro.put("foto", url.getText().toString())
                    val cant = bd.update("clientes", registro, "id=${id.toString()}", null)
                    bd.close()
                    nombre.setText("")
                    direccion.setText("")
                    telefono.setText("")
                    correo.setText("")
                    url.setText("")
                    if (cant == 1)
                        Toast.makeText(
                            this,
                            "se modifico el cliente con el id ${id.toString()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    else
                        Toast.makeText(this,
                            "no existe el cliente con el id ${id.toString()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    val intento4 = Intent(this, MainActivity::class.java)
                    startActivity(intento4)
                } else {
                    Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }

        }

        eliminarBtn.setOnClickListener {
            try {
                val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
                val bd = admin.writableDatabase
                val cant = bd.delete("clientes", "id=${id.toString()}", null)
                bd.close()
                nombre.setText("")
                direccion.setText("")
                telefono.setText("")
                correo.setText("")
                url.setText("")
                if (cant == 1)
                    Toast.makeText(
                        this,
                        "Se borro el cliente con el id ${id.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                else
                    Toast.makeText(
                        this,
                        "No existe un cliente con el id ${id.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                val intento5 = Intent(this, MainActivity::class.java)
                startActivity(intento5)
            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}