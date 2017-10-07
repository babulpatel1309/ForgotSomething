package com.forgotsomething

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import com.forgotsomething.Adapters.KotlinAdapter
import com.forgotsomething.Beans.ForgotDetail
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_details_to_remind_dialog.view.*

class MainActivity : BaseActivity() {

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

            AlertDialog.Builder(context)
                    .setView(view)
                    .setPositiveButton("Done", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {

                            if (getTextLength(view.etDetails) <= 0) {
                                toast("Please enter details")
                                return
                            }
                            if (getTextLength(view.etPlace) <= 0) {
                                toast("Please enter place")
                                return
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
                                p0?.dismiss()

                            }

                        }
                    })
                    .show()

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
