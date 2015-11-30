package com.esolz.su.zipstar.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by su on 8/7/15.
 */
public class MyriadProBoldEdit extends EditText {


    public MyriadProBoldEdit(Context context) {
        super(context);
        init();
    }



    public MyriadProBoldEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyriadProBoldEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init()
    {
        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                "MyriadPro-Bold.otf"));
    }
}
