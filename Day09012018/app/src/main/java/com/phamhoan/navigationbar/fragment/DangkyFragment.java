package com.phamhoan.navigationbar.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.phamhoan.navigationbar.MainActivity;
import com.phamhoan.navigationbar.R;
import com.phamhoan.navigationbar.constant.Key;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PHAMHOAN on 12/10/2017.
 */

public class DangkyFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private EditText edtEmail, edtPassword, edtRetype;
    private Button btnRegister;
    private String err;
    private RadioGroup rgCheck;
    private RadioButton rbDangky, rbDangnhap;
    private static final String TAG ="DangkyFragment";

    public boolean check = true;

    private ProgressDialog pDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dangky,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {
        rgCheck = rootView.findViewById(R.id.rg_check);
        edtEmail = rootView.findViewById(R.id.edt_email);
        edtPassword = rootView.findViewById(R.id.edt_password);
        edtRetype = rootView.findViewById(R.id.edt_retype);
        btnRegister = rootView.findViewById(R.id.btn_register);
        rbDangky = rootView.findViewById(R.id.rb_dangky);
        rbDangnhap = rootView.findViewById(R.id.rb_dangnhap);


        btnRegister.setOnClickListener(this);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Đang đăng ký...");
        pDialog.setCanceledOnTouchOutside(false);

        rgCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbDangnhap.isChecked()) {
                    deleteTypeData();
                    check = true;
                    edtRetype.setEnabled(false);
                    edtRetype.setVisibility(View.GONE);
                    btnRegister.setText("ĐĂNG NHẬP");
                } else {
                    deleteTypeData();
                    check = false;
                    edtRetype.setEnabled(true);
                    edtRetype.setVisibility(View.VISIBLE);
                    btnRegister.setText("ĐĂNG KÝ");
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                checkAcount();
                break;
            default:
                break;
        }
    }

    private void pushServer(String email, String pass) {
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Map<String, String> params = new HashMap<>();
        params.put(Key.EMAIL, email);
        params.put(Key.PASSWORD, pass);
        String url;
        if (check == false) {
            url = Key.URL_DANG_KY;
        } else {
            url = Key.URL_DANG_NHAP;
        }
        final JSONObject jsonObject = new JSONObject(params);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String description = response.getString("Description");
                            int result = response.getInt("ResultCode");

                            Toast.makeText(getContext(), description+" - "+result, Toast.LENGTH_LONG).show();
                            if ((result == 0) && (check ==false)) {
                                JSONObject jsonObject1 = response.getJSONObject("Data");
                                int Id = jsonObject1.getInt("ID");
                                Log.d(TAG, "ID: " + Id);
                                ((MainActivity)getActivity()).setId(Id+"");
                            }else{
                                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            Log.d(TAG, "onResponse: "+ e.toString());
                            e.printStackTrace();
                        }

                        pDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        pDialog.dismiss();

                    }
                }) {

        };


        requestQueue.add(jsonArrayRequest);
    }

    private void checkAcount() {
        err = "";
        String email = edtEmail.getText().toString();
        String pass = edtPassword.getText().toString();
        String retype = edtRetype.getText().toString();
        if (!check) {
            if (pass.isEmpty() || retype.isEmpty() || email.isEmpty()) {
                Toast.makeText(getContext(), " Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            if (pass.isEmpty() || email.isEmpty()) {
                Toast.makeText(getContext(), " Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (checkEmail(email) && checkPassword(pass, retype)) {
            pushServer(email, pass);
        } else {
            Toast.makeText(getContext(), err, Toast.LENGTH_SHORT).show();
        }

    }


    private boolean checkPassword(String pass, String retype) {
        if (!check) {

            if (pass.length() < 6) {
                err = "Mật khẩu từ 6 ký tự trở lên";
                return false;
            }
            if (!pass.equals(retype)) {
                err = "Mật khẩu không khớp";
                return false;
            }
            return true;
        } else return true;
    }

    private boolean checkEmail(String textEmail) {
        boolean match = textEmail.matches("([a-zA-Z])([A-Za-z0-9]){4,100}@(.*)\\.(.*)[^//.]$");
        if (!match) {
            err = "Không đúng định dạng email";
            return false;

        }
        return true;

    }

    private void deleteTypeData() {
        edtEmail.setText("");
        edtPassword.setText("");
        edtRetype.setText("");
    }
}
