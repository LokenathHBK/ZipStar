package com.esolz.su.zipstar.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by su on 8/7/15.
 */
public class MyriadProLight extends TextView {


    public MyriadProLight(Context context) {
        super(context);
        init();
    }



    public MyriadProLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyriadProLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init()
    {
        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                "MyriadPro-Light.otf"));
    }
}
