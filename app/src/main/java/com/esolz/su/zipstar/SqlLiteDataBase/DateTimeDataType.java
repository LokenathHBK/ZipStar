package com.esolz.su.zipstar.SqlLiteDataBase;

import com.esolz.su.zipstar.DataType.APP_DATA;

/**
 * Created by su on 14/10/15.
 */
public class DateTimeDataType {
    String id;
    String Prefered_time= APP_DATA.prefered_time;

    public DateTimeDataType(String prefered_time) {
        Prefered_time = prefered_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefered_time() {
        return Prefered_time;
    }

    public void setPrefered_time(String prefered_time) {
        Prefered_time = prefered_time;
    }
}
