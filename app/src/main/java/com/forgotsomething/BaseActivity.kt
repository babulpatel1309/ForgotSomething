package com.forgotsomething

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm

/**
 * Created by Harsh on 6/10/17.
 */

open class BaseActivity : AppCompatActivity() {

    var context: Context = this
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        realm = Realm.getDefaultInstance()
    }

    fun getText(et: EditText): String {
        return et.text.toString().trim()
    }

    fun getTextLength(et: EditText): Int {
        return et.text.toString().trim().length
    }

    fun toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}