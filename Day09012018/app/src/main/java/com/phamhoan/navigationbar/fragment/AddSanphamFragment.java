package com.phamhoan.navigationbar.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PHAMHOAN on 12/11/2017.
 */

public class AddSanphamFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private EditText edtTenSanPham;
    private Spinner spinner;
    private Button btnThemSanPham;
    private ProgressDialog pDialog;
    private String danhMuc;
    private static final String TAG = "AddSanphamFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_themsanpham, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading...");
        pDialog.setCanceledOnTouchOutside(false);
        btnThemSanPham = rootView.findViewById(R.id.btn_themsanpham);
        edtTenSanPham = rootView.findViewById(R.id.edt_tensanpham);
        spinner = rootView.findViewById(R.id.spinner);
        creatSpinner();

        btnThemSanPham.setOnClickListener(this);
    }

    private void creatSpinner() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("Linh kiện");
        list.add("Laptop");
        list.add("Màn hình");
        list.add("Phụ kiện");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                danhMuc = list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_themsanpham:
                themSanPham();

                break;
            default:
                break;
        }
    }

    private void themSanPham() {


        final String tenSP;
        tenSP = edtTenSanPham.getText().toString();
        if (tenSP.isEmpty()) {
            Toast.makeText(getContext(), "Hãy nhập đủ thông tin", Toast.LENGTH_LONG).show();
            return;
        }

        final String iD = ((MainActivity) getActivity()).getId();

        final long unixTime = System.currentTimeMillis() / 1000L;

        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Map<String, String> params = new HashMap<>();
        params.put(Key.TENSP, tenSP);
        params.put(Key.DANH_MUC, danhMuc);
        params.put(Key.DATE, String.valueOf(unixTime));
        params.put(Key.USER_ID, iD);

        JSONObject jsonObject = new JSONObject(params);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.POST, Key.URL_THEM_SP, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = response.getString("Description");
                            Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
                            Toast.makeText(getContext(), tenSP + danhMuc + unixTime + iD, Toast.LENGTH_LONG).show();
                            Log.d(TAG, tenSP + danhMuc + String.valueOf(unixTime) + iD);


                        } catch (JSONException e) {
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
                });


        requestQueue.add(jsonArrayRequest);

    }

}
