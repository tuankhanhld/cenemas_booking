package com.kotlin.tuank.tk_cenemas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import cenemas.database.CardFilmData
import cenemas.database.SeatCategoryData
import functionAppApear.ChangeStatusBarColor

class HomeScreen : AppCompatActivity() {
    lateinit var seatCategoriesRc: RecyclerView
    lateinit var filmCategoriesRc: RecyclerView
    lateinit var eventCategoriesRc: RecyclerView
    lateinit var userInfor: LinearLayout

    var seatCateList = arrayListOf<String>("Movies", "Events", "Plays", "Sport", "Activity", "Challenge")
    var seatCateIcons = arrayListOf<Int>(
            R.drawable.movie_seat_cate_ic,
            R.drawable.event_seat_cate_ic,
            R.drawable.play_seat_cate_ic,
            R.drawable.sport_seat_cate_ic,
            R.drawable.activity_seat_cate_ic,
            R.drawable.monum_seat_cate_ic)
    var filmsCateList = arrayListOf<String>("Spider Man", "King", "Galaxy", "Beach", "Tower", "Black panther", "Go Go")
    var EventsCateList = arrayListOf<String>("Speed top", "Ghost", "Hero hill", "Man", "Tower", "Black panther", "Go Go")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val changeStatusBarColor: ChangeStatusBarColor = ChangeStatusBarColor()
        changeStatusBarColor.changeSatusColor(R.color.colorStatusHome, this@HomeScreen)

        seatCategoriesRc = findViewById(R.id.seatCategoriesRc)
        filmCategoriesRc = findViewById(R.id.filmCategoriesRC)
        eventCategoriesRc = findViewById(R.id.eventCategoriesRc)
        userInfor = findViewById(R.id.userInfor)

        addSeatCategoriesRc()
        addFilmsRc()
        addEventsRc()
        setClickOnUserInfor()
    }

    private fun addEventsRc() {
        eventCategoriesRc.layoutManager =
                LinearLayoutManager(this@HomeScreen, LinearLayout.HORIZONTAL, false)
        var eventsCategoriesData = ArrayList<CardFilmData>()

        for (i in 0..(EventsCateList.size - 1)){
            eventsCategoriesData.add(CardFilmData("https://firebasestorage.googleapis.com/v0/b/fuelapp-c00b1.appspot.com/o/default%20avt%2Fevent_img_demo%402x.png?alt=media&token=960e7657-9cb5-4c4b-a44b-23eabd2b80df", EventsCateList[i], i*2, i))
        }
        var adapter = CustomRecycleViewFilms(eventsCategoriesData)
        eventCategoriesRc.adapter = adapter
    }

    private fun addSeatCategoriesRc() {
        seatCategoriesRc.layoutManager =
            LinearLayoutManager(this@HomeScreen, LinearLayout.HORIZONTAL, false)
        var seatCategoriesData = ArrayList<SeatCategoryData>()
        for (i in 0..(seatCateList.size - 1)){
            seatCategoriesData.add(SeatCategoryData(seatCateIcons[i], seatCateList[i]))
        }

        var adapter = CustomRecycleViewAdapterCategories(seatCategoriesData)
        seatCategoriesRc.adapter = adapter
    }

    private fun addFilmsRc() {
        filmCategoriesRc.layoutManager =
                LinearLayoutManager(this@HomeScreen, LinearLayout.HORIZONTAL, false)
        var filmsCategoriesData = ArrayList<CardFilmData>()

        for (i in 0..(filmsCateList.size - 1)){
            filmsCategoriesData.add(CardFilmData("https://firebasestorage.googleapis.com/v0/b/fuelapp-c00b1.appspot.com/o/default%20avt%2Fcard_img_demo%402x.png?alt=media&token=bb124ecb-cfa8-4437-8f5a-706a3a247f74", filmsCateList[i], i*2, i))
        }
        var adapter = CustomRecycleViewFilms(filmsCategoriesData)
        filmCategoriesRc.adapter = adapter
    }

    private fun setClickOnUserInfor(){
        userInfor.setOnClickListener { view ->
            startActivity(Intent(this@HomeScreen, UserInformationMenu :: class.java))
        }
    }

}
