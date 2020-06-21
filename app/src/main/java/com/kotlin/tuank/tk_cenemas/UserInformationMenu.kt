package com.kotlin.tuank.tk_cenemas

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import cenemas.core_enums.user_menu_items_name
import cenemas.database.UserMenuItem

class UserInformationMenu : AppCompatActivity() {
    lateinit var userMenuAvatar: ImageView
    lateinit var userName: TextView
    lateinit var userMenuList: ListView
    lateinit var userMenuLogout: TextView
    lateinit var logout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information_menu)
        this.userMenuAvatar = findViewById(R.id.userMenuAvatar)
        this.userName = findViewById(R.id.userName)
        this.userMenuList = findViewById(R.id.userMenuList)
        this.userMenuLogout = findViewById(R.id.userMenuLogout)
        this.logout = findViewById(R.id.logout)
        configUserMenuListView()
    }

    private fun configUserMenuListView(){
        val UserMenuItems = arrayListOf<UserMenuItem>(
            UserMenuItem(R.drawable.user_ic, user_menu_items_name.MY_PROFILE.itemName),
            UserMenuItem(R.drawable.share_ic, user_menu_items_name.REQUESTS.itemName),
            UserMenuItem(R.drawable.message_ic, user_menu_items_name.MESSAGES.itemName),
            UserMenuItem(R.drawable.map_pin_ic, user_menu_items_name.LOCATION.itemName),
            UserMenuItem(R.drawable.settings_ic, user_menu_items_name.SETTINGS.itemName)
        )
        val UserMenuAdapter = CustomListViewUserMenu(this@UserInformationMenu, UserMenuItems)
        this.userMenuList.adapter = UserMenuAdapter
    }


}
