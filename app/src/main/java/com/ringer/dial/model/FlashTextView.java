package com.ringer.dial.model;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.ringer.R;

/**
 * Created by Tam Le Thanh on 3/24/18.
 */

public class FlashTextView extends AppCompatTextView {
    public FlashTextView(Context context) {
        super(context);
    }

    public FlashTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        MetricUtils.setCustomFontAble(this, context, attrs,
                R.styleable.FlashTextView, R.styleable.FlashTextView_fontTypeFaceName,
                R.styleable.FlashTextView_fontSize, R.styleable.FlashTextView_fontSizeSystem, R.styleable.FlashTextView_fontSizeScale);
    }

    public FlashTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        MetricUtils.setCustomFontAble(this, context, attrs,
                R.styleable.FlashTextView, R.styleable.FlashTextView_fontTypeFaceName,
                R.styleable.FlashTextView_fontSize, R.styleable.FlashTextView_fontSizeSystem, R.styleable.FlashTextView_fontSizeScale);
    }
}
