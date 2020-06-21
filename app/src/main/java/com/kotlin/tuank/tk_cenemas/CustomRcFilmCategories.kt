package com.kotlin.tuank.tk_cenemas

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cenemas.database.CardFilmData
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class CustomRecycleViewFilms constructor(var arrayRc: ArrayList<CardFilmData>): RecyclerView.Adapter<CustomRecycleViewFilms.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        var v = LayoutInflater.from(p0?.context).inflate(R.layout.card_film_item,p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayRc.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val data: CardFilmData = arrayRc[p1]
        p0?.nameTitle.text = data.cardFilmTitle
        p0?.likeNumber.text = data.likeNumber.toString()
        p0?.startNumber.text = data.startNumber.toString()
        Picasso.get()
            .load(data.cardImg)
            .into(p0?.cardImg)
    }

    class ViewHolder(row: View): RecyclerView.ViewHolder(row){
        var cardImg: ImageView
        var nameTitle: TextView
        var likeNumber: TextView
        var startNumber: TextView
        init {
            cardImg = row.findViewById(R.id.cardImg)
            nameTitle = row.findViewById(R.id.nameTitle)
            likeNumber = row.findViewById(R.id.likeNumber)
            startNumber = row.findViewById(R.id.startNumber)
        }
    }
}