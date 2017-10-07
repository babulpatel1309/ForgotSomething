package com.forgotsomething.Beans

import android.support.annotation.NonNull
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Harsh on 6/10/17.
 */

open class ForgotDetail() : RealmObject() {



    @PrimaryKey
    var id: Int = 0
    var details: String = ""
    var places: String = ""
}
