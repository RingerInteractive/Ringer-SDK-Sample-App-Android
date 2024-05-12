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
        this.zero = this.mLayoutView.findViewById(R.id.zero_lay);
        this.clear = (AppCompatImageView) this.mLayoutView.findViewById(R.id.clear_lay);
        this.mLayoutView.findViewById(R.id.zero_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.one_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.two_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.three_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.four_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.five_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.six_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.seven_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.eight_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.nine_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.star_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.sign_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.clearAll_lay).setOnClickListener(this);
        this.mLayoutView.findViewById(R.id.call_lay).setOnClickListener(this);
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

            case R.id.eight_lay:
                numpad = "8";
                break;
            case R.id.five_lay:
                numpad = "5";
                break;
            case R.id.four_lay:
                numpad = "4";
                break;
            case R.id.nine_lay:
                numpad = "9";
                break;
            case R.id.one_lay:
                numpad = "1";
                break;
            case R.id.seven_lay:
                numpad = "7";
                break;
            case R.id.sign_lay:
                numpad = "#";
                break;
            case R.id.six_lay:
                numpad = "6";
                break;
            case R.id.star_lay:
                numpad = "*";
                break;
            case R.id.three_lay:
                numpad = "3";
                break;
            case R.id.two_lay:
                numpad = "2";
                break;
            case R.id.zero_lay:
                numpad = "0";
                break;
            case R.id.call_lay:
                callClick();
                break;
            case R.id.clearAll_lay:
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
