package com.example.proyecto

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso

class MyListAdapter(private val context: Activity, private val arrayNombre: Array<String>, private val arrayDireccion: Array<String>, private val arrayUrlImg: Array<String>, private val id: Array<Int>)
    : ArrayAdapter<Int>(context, R.layout.custom_list, id) {

    override fun getView(pos: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)
        //val campoId = rowView.findViewById(R.id.IdCL) as TextView
        val campoNombre = rowView.findViewById(R.id.nombreCL) as TextView
        val campoImagen = rowView.findViewById(R.id.iconoCL) as ImageView
        val campoDireccion = rowView.findViewById(R.id.direccionCL) as TextView
        campoNombre.text = arrayNombre[pos]
        Picasso.get().load(arrayUrlImg[pos]).into(campoImagen);
        campoDireccion.text = arrayDireccion[pos]
        //campoId.text = id[pos].toString()
        return rowView
    }
}

