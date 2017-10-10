package com.forgotsomething.Main

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import com.forgotsomething.Adapters.KotlinAdapter
import com.forgotsomething.Beans.ForgotDetail
import com.forgotsomething.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_details_to_remind_dialog.view.*

open class MainActivity : BaseActivity() {

    lateinit var adapter: KotlinAdapter
    lateinit var allSchedules: ArrayList<ForgotDetail>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        allSchedules = ArrayList()

        allSchedules.addAll(realm.where(ForgotDetail::class.java).findAll())

        recycler.layoutManager = LinearLayoutManager(context)
        adapter = KotlinAdapter(context, allSchedules)
        recycler.adapter = adapter

        btnAdd.setOnClickListener {

            var view = LayoutInflater.from(context).inflate(R.layout.custom_details_to_remind_dialog, null)

            var dialog = AlertDialog.Builder(context)
                    .setView(view)
                    .show()


            view.btnDone.setOnClickListener {

                if (getTextLength(view.etDetails) <= 0) {
                    toast("Please enter details")
                    return@setOnClickListener
                }
                if (getTextLength(view.etPlace) <= 0) {
                    toast("Please enter place")
                    return@setOnClickListener
                }


                realm.executeTransaction {

                    var id = realm.where(ForgotDetail::class.java).max("id")
                    if (id == null) {
                        id = 0
                    }

                    val details = ForgotDetail()
                    details.places = getText(view.etPlace)
                    details.details = getText(view.etDetails)

                    realm.createObject(details::class.java, plus(id as Int))

                    updateUI()

                }

                dialog.dismiss()
            }


        }

    }

    fun updateUI() {
        allSchedules.clear()
        allSchedules.addAll(realm.where(ForgotDetail::class.java).findAll())

        adapter.notifyDataSetChanged()
    }
}


private fun plus(i: Int): Int {
    return i + 1
}
