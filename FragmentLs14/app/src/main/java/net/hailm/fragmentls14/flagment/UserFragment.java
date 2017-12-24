package net.hailm.fragmentls14.flagment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import net.hailm.fragmentls14.adapter.CongViecAdapter;
import net.hailm.fragmentls14.dialog.AddCongViecDialog;
import net.hailm.fragmentls14.model.CongViec;
import net.hailm.fragmentls14.model.DataBase;
import net.hailm.fragmentls14.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai_l on 15/11/2017.
 */

public class UserFragment extends Fragment {
    private View rootView;
    private ImageView btnAdd;
    private DataBase dataBase;
    private RecyclerView rcvCongViec;
    private List<CongViec> congViecs;
    private CongViecAdapter congViecAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();
    }

    private void initializeComponents() {
        congViecs = new ArrayList<>();

        rcvCongViec = rootView.findViewById(R.id.rcv_congviec);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rcvCongViec.setLayoutManager(llm);

        congViecAdapter = new CongViecAdapter(congViecs, getActivity());
        rcvCongViec.setAdapter(congViecAdapter);

        btnAdd = rootView.findViewById(R.id.btn_add);
        // Tạo database ghi chú
        dataBase = new DataBase(getActivity(), "ghichu.sqlite", null, 1);

        // Tạo bảng công việc
        dataBase.queryData(
                "CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "TenCV VARCHAR(200))");

        // InsertData
//        dataBase.queryData("INSERT INTO CongViec VALUES(null,'Viết ứng dụng...')");

        // Select data

        getDataCongViec();
        addDataCongViec();
    }

    private void getDataCongViec() {
        Cursor dataCongViec = dataBase.getData("SELECT * FROM CongViec");
        congViecs.clear();
        while (dataCongViec.moveToNext()) {
            String name = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
//            Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
            congViecs.add(new CongViec(id, name));
        }
        congViecAdapter.notifyDataSetChanged();
    }

    private void addDataCongViec() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });
    }

    private void showDialogAdd() {
        AddCongViecDialog dialog = new AddCongViecDialog(getActivity());
        getDataCongViec();
        dialog.show();
    }

}
