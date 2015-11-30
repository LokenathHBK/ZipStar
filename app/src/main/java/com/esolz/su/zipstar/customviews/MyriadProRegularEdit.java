package com.esolz.su.zipstar.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by su on 8/7/15.
 */
public class MyriadProRegularEdit extends EditText {


    public MyriadProRegularEdit(Context context) {
        super(context);
        init();
    }



    public MyriadProRegularEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyriadProRegularEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init()
    {
        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                "MyriadPro-Regular.otf"));
    }
}
