package com.phamhoan.navigationbar.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.phamhoan.navigationbar.IDataBack;
import com.phamhoan.navigationbar.MainActivity;
import com.phamhoan.navigationbar.OnLoadMoreListener;
import com.phamhoan.navigationbar.R;
import com.phamhoan.navigationbar.adapter.ProductAdapter;
import com.phamhoan.navigationbar.constant.Key;
import com.phamhoan.navigationbar.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PHAMHOAN on 12/11/2017.
 */

public class DanhSachFragment extends Fragment implements IDataBack {
    private static final String TAG = "DanhSachFragment1";
    private View rootView;
    private RecyclerView rcvProduct;
    private List<Product> productList;
    private ProductAdapter productAdapter;
    private ProgressDialog pDialog;
    private LinearLayoutManager llm;
    private EditText edtSearch;
    private int page;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_danhsach, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {
        page = 1;
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading...");

        edtSearch = rootView.findViewById(R.id.edt_Search);
        rcvProduct = rootView.findViewById(R.id.rcv_product);
        llm = new LinearLayoutManager(getContext());
        productList = new ArrayList<>();
        rcvProduct.setLayoutManager(llm);
        productAdapter = new ProductAdapter(getContext(), rcvProduct);
        getData(page);


        productAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                productList = productAdapter.getProductList();
                Log.d(TAG, "Total: " + ((MainActivity)getActivity()).getTotal());
                if (productList.size() < ((MainActivity)getActivity()).getTotal()) {
                    Log.d(TAG, "laysize: " + productList.size());
                    productList.add(null);
                    productAdapter.notifyItemInserted(productList.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            productList.remove(productList.size() - 1);
                            productAdapter.notifyItemRemoved(productList.size());
                            page++;
                            Log.d(TAG, "run: " + page);
                            getData(page);
                        }
                    }, 5000);

                } else {
                    Toast.makeText(getActivity(), "Loading data completed", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


    private void getData(final int page) {
        if (page == 1) {
            pDialog.show();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Map<String, Integer> params = new HashMap<>();
        params.put("UserID", Integer.valueOf(((MainActivity) getActivity()).getId()));
        params.put("PageNum", page);
        Log.d(TAG, "Page: " + page);
        JSONObject jsonObject = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Key.URL_GET_DS, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<Product> arr = new ArrayList<>();
                            String description = response.getString("Description");
                            Toast.makeText(getContext(), description, Toast.LENGTH_LONG).show();
                            JSONObject jsonArray = response.getJSONObject("Data");
                            JSONArray jsonArray1 = jsonArray.getJSONArray("ListProduct");
                            String total = jsonArray.getString("Total");

                            for (int i = 0; i < jsonArray1.length(); i++) {
                                JSONObject object = (JSONObject) jsonArray1.get(i);
                                String name = object.getString(Key.TENSP);
                                String category = object.getString("Category");
                                String createdDate = object.getString("CreatedDate");
                                int iD = object.getInt("ID");
                                int userID = object.getInt("UserID");
                                Product product = new Product(name, category, createdDate, iD, userID);
                                arr.add(product);
                            }
                            ((MainActivity)getActivity()).setTotal(Integer.parseInt(total));
                            productAdapter.addList(arr);
                            productAdapter.setDataBack(DanhSachFragment.this);
                            if (page == 1) {
                                rcvProduct.setAdapter(productAdapter);
                                setupEditSearch();
                                pDialog.dismiss();
                            } else {
                                productAdapter.notifyDataSetChanged();
                                productAdapter.setLoaded();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();

            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void dataBack(int position, int type) {
        Product product = productAdapter.getProductList().get(position);
        Bundle bundle = new Bundle();
        bundle.putString("Name", product.getName());
        bundle.putString("Category", product.getCategory());
        bundle.putString("Date", product.getCreatedDate());
        bundle.putInt("ID", product.getiD());
        bundle.putInt("UserID", product.getUserID());
        Fragment newFragment = null;
        if (type == 1) {
            newFragment = new ResultFragment();
        } else if (type == 2) {
            newFragment = new EditFragment();
        }
        newFragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.lnl_show, newFragment)
                .show(newFragment)
                .addToBackStack(null)
                .hide(this)
                .commit();


    }

    @Override
    public void dataDelete(int position) {
        Product product = productAdapter.getProductList().get(position);
        int IDproduct = product.getiD();
        int IDUser = product.getUserID();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Map<String, Integer> params = new HashMap<>();
        params.put(Key.ID, IDproduct);
        params.put(Key.USER_ID, IDUser);
        JSONObject jsonObject = new JSONObject(params);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.POST, Key.URL_DELETE_PRODUCT, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = response.getString("Description");
                            Toast.makeText(getContext(), "Xóa " + result, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);

    }


    int a = 1;
    private Comparator<Product> sortByDate = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            int result = (int) (Long.parseLong(o1.getCreatedDate()) - Long.parseLong(o2.getCreatedDate()));
            return result * a;
        }
    };

    public void sortData() {
        a *= -1;
        productList = productAdapter.getProductList();
        Collections.sort(productList, sortByDate);
        productAdapter = new ProductAdapter(getContext(), rcvProduct);
        productAdapter.setProductList(productList);
        productAdapter.setDataBack(DanhSachFragment.this);
        rcvProduct.setAdapter(productAdapter);
        setupEditSearch();

    }
    private void setupEditSearch() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                productAdapter.filter(edtSearch.getText().toString());

            }
        });

    }


}
