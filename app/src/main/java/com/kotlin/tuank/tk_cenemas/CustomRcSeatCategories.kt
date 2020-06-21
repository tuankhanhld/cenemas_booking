package com.kotlin.tuank.tk_cenemas

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cenemas.database.SeatCategoryData
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class CustomRecycleViewAdapterCategories constructor(var arrayRc: ArrayList<SeatCategoryData>): RecyclerView.Adapter<CustomRecycleViewAdapterCategories.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        var v = LayoutInflater.from(p0?.context).inflate(R.layout.seat_categorie_item,p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayRc.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val data: SeatCategoryData = arrayRc[p1]
        p0?.categoryTitle.text = data.cardTitle
        p0?.imageCategory.setImageResource(data.cardImage)
//        Picasso.get()
//            .load(data.cardImage)
//            .into(object: Target {
//                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
//                    p0?.imageCategory.setImageBitmap(bitmap)
//                }
//                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
//                }
//                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
//                }
//
//            })
    }

    class ViewHolder(row: View): RecyclerView.ViewHolder(row){
        var imageCategory: ImageView
        var categoryTitle: TextView
        init {
            imageCategory = row.findViewById(R.id.imageCategory)
            categoryTitle = row.findViewById(R.id.categoryTitle)
        }
    }
}