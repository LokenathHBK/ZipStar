package com.esolz.su.zipstar.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by su on 8/7/15.
 */
public class MyriadProLightEdit extends EditText {


    public MyriadProLightEdit(Context context) {
        super(context);
        init();
    }



    public MyriadProLightEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyriadProLightEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init()
    {
        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                "MyriadPro-Light.otf"));
    }
}
