package com.forgotsomething

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Harsh on 6/10/17.
 */

open class ApplicationClass : Application() {


    override fun onCreate() {
        super.onCreate()

        Realm.init(applicationContext)
        val realmConfiguration = RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(realmConfiguration)

    }

}