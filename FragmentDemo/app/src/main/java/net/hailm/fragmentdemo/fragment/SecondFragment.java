package net.hailm.fragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import net.hailm.fragmentdemo.R;

/**
 * Created by hai_l on 21/01/2018.
 */

public class SecondFragment extends Fragment {
    private View rootView;
    private EditText edtName, edtEmail;
    private Button btnSubmit;
    private OnClickButtonListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_second, container, false);
        initView();
        return rootView;
    }

    public void setListener(OnClickButtonListener listener) {
        this.listener = listener;
    }

    private void initView() {
        edtName = rootView.findViewById(R.id.edt_name);
        edtEmail = rootView.findViewById(R.id.edt_email);
        btnSubmit = rootView.findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickButton();
            }
        });
    }

    public interface OnClickButtonListener {
        void clickButton();
    }

}
