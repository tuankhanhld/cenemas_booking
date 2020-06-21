package com.kotlin.tuank.tk_cenemas

import cenemas.database.UserMenuItem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomListViewUserMenu constructor(var context: Context, var userMenuItems: ArrayList<UserMenuItem>): BaseAdapter(){
    class ViewHolder(row: View){
        var menuItemIcon: ImageView
        var menuItemText: TextView

        init {
            menuItemIcon = row.findViewById(R.id.menuItemIcon)
            menuItemText = row.findViewById(R.id.menuItemText)
        }
    }

    override fun getView(position: Int, convertview: View?, p2: ViewGroup?): View {
        var view: View?
        var viewHolder : ViewHolder
        if (convertview == null){
            var layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.user_menu_item, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertview
            viewHolder = convertview.tag as ViewHolder
        }
        var userItem: UserMenuItem = getItem(position) as UserMenuItem
        viewHolder.menuItemIcon.setImageResource(userItem.srcIcon)
        viewHolder.menuItemText.text = userItem.textItem

        return view as View
    }

    override fun getItem(p0: Int): Any {
        return userMenuItems.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return userMenuItems.size
    }

}