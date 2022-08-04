package com.ringer.dial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ringer.R;
import com.ringer.dial.model.FlashTextView;


public class DialPadDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    public static DialPadDialogFragment getInstance() {
        return new DialPadDialogFragment();
    }

    AppCompatImageView clear;
    FlashTextView inputNumber;
    protected View mLayoutView;
    View zero;
    public OnPhoneChangeListener onPhoneChangeListener;


    public interface OnPhoneChangeListener {
        void onPhoneChange(String str);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.mLayoutView = inflater.inflate(R.layout.dialog_dial_pad, container, false);
        init();
        return this.mLayoutView;
    }

    private void init() {
        this.inputNumber = mLayoutView.findViewById(R.id.inputNumber);
        this.zero = this.mLayoutView.findViewById(R.id.zero);
        this.clear = (AppCompatImageView) this.mLayoutView.findViewById(R.id.clear);
        this.mLayoutView.findViewById(R.id.zero).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.one).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.two).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.three).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.four).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.five).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.six).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.seven).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.eight).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.nine).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.star).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.sign).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.clearAll).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.call).setOnClickListener(this);
        // this.zero.setOnLongClickListener(new DialPadDialogFragment$$ExternalSyntheticLambda4(this));
        // this.clear.setOnClickListener(new DialPadDialogFragment$$ExternalSyntheticLambda2(this));
        //  this.clear.setOnLongClickListener(new DialPadDialogFragment$$ExternalSyntheticLambda5(this));
        this.inputNumber.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && DialPadDialogFragment.this.onPhoneChangeListener != null) {
                    DialPadDialogFragment.this.onPhoneChangeListener.onPhoneChange(s.toString());
                }
            }
        });

        zero.setOnLongClickListener(v -> {
            inputNumber.append("+");
            return true;
        });
        clear.setOnClickListener(v -> {
            int length = inputNumber.getText().length();
            if (length > 0) {
                inputNumber.setText(inputNumber.getText().subSequence(0, length - 1));
            }
        });
        clear.setOnLongClickListener(v -> {
            inputNumber.setText("");
            return true;
        });
    }

    @Override
    public void onClick(View view) {
        String numpad = "";
        switch (view.getId()) {
            case R.id.eight:
                numpad = "8";
                break;
            case R.id.five:
                numpad = "5";
                break;
            case R.id.four:
                numpad = "4";
                break;
            case R.id.nine:
                numpad = "9";
                break;
            case R.id.one:
                numpad = "1";
                break;
            case R.id.seven:
                numpad = "7";
                break;
            case R.id.sign:
                numpad = "#";
                break;
            case R.id.six:
                numpad = "6";
                break;
            case R.id.star:
                numpad = "*";
                break;
            case R.id.three:
                numpad = "3";
                break;
            case R.id.two:
                numpad = "2";
                break;
            case R.id.zero:
                numpad = "0";
                break;
            case R.id.call:
                callClick();
                break;
            case R.id.clearAll:
                clearAllClick();
                break;
        }
        this.inputNumber.append(numpad);
    }

    public void clearAllClick() {
        this.inputNumber.setText("");
    }

    public void callClick() {
        String phoneNumber = this.inputNumber.getText().toString();
        if (!TextUtils.isEmpty(phoneNumber)) {
            Intent intent = new Intent("android.intent.action.CALL");
            intent.setData(Uri.parse("tel:" + phoneNumber.trim()));
            getActivity().startActivity(intent);
        }
    }

}
