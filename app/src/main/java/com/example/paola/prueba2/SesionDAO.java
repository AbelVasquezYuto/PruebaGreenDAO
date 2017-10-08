package com.example.paola.prueba2;

import android.app.Application;

import com.example.paola.prueba2.model.DaoMaster;
import com.example.paola.prueba2.model.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Paola on 08/10/2017.
 */

public class SesionDAO extends Application{

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"mydb");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
