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
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PHAMHOAN on 12/27/2017.
 */

public class EditFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private TextView userID, ID;
    private EditText productName;
    private Spinner spinner;
    private Button btnEdit;
    private String category, idProduct;
    private ProgressDialog pDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit, container, false);
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
        userID = rootView.findViewById(R.id.txt_UserID);
        ID = rootView.findViewById(R.id.txt_ID);
        productName = rootView.findViewById(R.id.edt_product_name);
        spinner = rootView.findViewById(R.id.spinner);
        btnEdit = rootView.findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(this);

        Bundle bundle = getArguments();
        ID.setText("ID: " + bundle.getInt("ID"));
        idProduct = bundle.getInt("ID") + "";
        userID.setText("UserID: " + bundle.getInt("UserID"));
        productName.setText(bundle.getString("Name"));
        category = bundle.getString("Category");
        creatSpinner();
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
        int spinnerPosition = arrayAdapter.getPosition(category);
        spinner.setSelection(spinnerPosition);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:
                editProduct();

                break;
            default:
                break;
        }
    }

    private void editProduct() {
        final String tenSP;
        tenSP = productName.getText().toString();
        if (tenSP.isEmpty()) {
            Toast.makeText(getContext(), "Hãy nhập đủ thông tin", Toast.LENGTH_LONG).show();
            return;
        }
        pDialog.show();
        final String iD = ((MainActivity) getActivity()).getId();
        final long unixTime = System.currentTimeMillis() / 1000L;

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Map<String, String> params = new HashMap<>();
        params.put(Key.USER_ID, iD);
        params.put(Key.ID, idProduct);
        params.put(Key.TENSP, tenSP);
        params.put(Key.DANH_MUC, category);
        params.put(Key.DATE, String.valueOf(unixTime));
        params.put(Key.USER_ID, iD);

        JSONObject jsonObject = new JSONObject(params);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.POST, Key.URL_EDIT_PRODUCT, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = response.getString("Description");
                            Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
                            pDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
        ((MainActivity) getActivity()).callFragment(Key.DANHSACH);
    }

}

