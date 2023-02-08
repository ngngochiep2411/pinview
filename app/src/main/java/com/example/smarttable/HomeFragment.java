package com.example.smarttable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.column.ArrayColumn;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.bg.BaseBackgroundFormat;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.format.bg.ICellBackgroundFormat;
import com.bin.david.form.data.format.draw.TextImageDrawFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.PageTableData;
import com.bin.david.form.data.table.TableData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    SmartTable<UserModel> smartTable;
//    SmartRefreshLayout smartRefreshLayout;
    TableData<UserModel> tableData;
    List<UserModel> listUser =new ArrayList<>();
    View view;
    private Map<String, Bitmap> map = new HashMap<>();
    Button btnReplace;
    NestedScrollView nestedScrollView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initTable();
    }

    private void initView() {
        smartTable = view.findViewById(R.id.smartTable);
//        smartRefreshLayout = view.findViewById(R.id.refreshLayout);
        btnReplace = view.findViewById(R.id.btnReplace);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);
        btnReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceData();
            }
        });

//        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener()
//        {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY)
//            {
//                if (v.getChildAt(v.getChildCount() - 1) != null)
//                {
//                    if (scrollY > oldScrollY)
//                    {
//                        if (scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight()))
//                        {
//                            addMoreData();
//                        }
//                    }
//                }
//            }
//        });
        smartTable.setNestedScrollingEnabled(true);

        smartTable.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                smartTable.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                smartTable.getLayoutParams().height = nestedScrollView.getMeasuredHeight();

                addMoreData();
            }
        });
    }

    private void replaceData() {
        listUser.clear();
        listUser.add(new UserModel("1", "Phạm Thiên An", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("2", "Trần Trung Hiếu", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("3", "Trần Ngọc Bảo", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("4", "Hoàng Nguyễn Phúc Nguyên Chương", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("5", "Nguyễn Thanh Danh", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("6", "Nguyễn Trần Quốc Duy", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("7", "Phan Đức Thanh Duy", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("8", "Trần Đình Hưng", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("9", "Trần Kiên Hưng", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("10", "Hứa Tuấn Hữu", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("11", "Hà Đình Đức Huy", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("12", "Bùi Hứa Xuân Huy", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("13", "Đặng Lê Quốc Khang", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("14", "Phạm Đức Quốc Khánh", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("15", "Võ Tiến Khoa", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("16", "Nguyễn Tiến Khoa", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("17", "Vũ Trung Kiên", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("18", "Nguyễn Thị Thùy Linh", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("19", "Nguyễn Đình Lộc", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("20", "Nguyễn Thành Lợi", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("21", "Võ Ngọc Nguyễn Minh", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("22", "Bùi Minh Nhật", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("23", "Đặng Hoàng Phi", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("24111", "Lê Hoàng Phúc", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));

        tableData.setT(listUser);
        smartTable.setTableData(tableData);
    }

    private void addMoreData() {
        Toast.makeText(getContext(),"loadmore",Toast.LENGTH_SHORT).show();
        for(int i=0;i<5;i++){
            listUser.add(new UserModel("434", "Phạm Thiên An", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
            listUser.add(new UserModel("14322", "Trần Trung Hiếu", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
            listUser.add(new UserModel("3432", "Trần Ngọc Bảo", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
            listUser.add(new UserModel("4", "Hoàng Nguyễn Phúc Nguyên Chương", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));

        }
        tableData.setT(listUser);
        smartTable.setTableData(tableData);
//        smartRefreshLayout.finishLoadMore();
    }

    private void initData() {
        listUser.add(new UserModel("\n", "Phạm Thiên An", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("\n\n", "Trần Trung Hiếu", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("\n\n\n", "Trần Ngọc Bảo", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("4", "Hoàng Nguyễn Phúc Nguyên Chương", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("5", "Nguyễn Thanh Danh", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("6", "Nguyễn Trần Quốc Duy", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("7", "Phan Đức Thanh Duy", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("8", "Trần Đình Hưng", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("9", "Trần Kiên Hưng", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("10", "Hứa Tuấn Hữu", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("11", "Hà Đình Đức Huy", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("12", "Bùi Hứa Xuân Huy", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("13", "Đặng Lê Quốc Khang", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("14", "Phạm Đức Quốc Khánh", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("15", "Võ Tiến Khoa", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("16", "Nguyễn Tiến Khoa", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("17", "Vũ Trung Kiên", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("18", "Nguyễn Thị Thùy Linh", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("19", "Nguyễn Đình Lộc", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("20", "Nguyễn Thành Lợi", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("21", "Võ Ngọc Nguyễn Minh", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("22", "Bùi Minh Nhật", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("23", "Đặng Hoàng Phi", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));
        listUser.add(new UserModel("24", "Lê Hoàng Phúc", "CXCXZCXZCZXCXC", "CXCZZCXCXC", "XCXZCXZCXCXXCC", "CXZCXZCZXCXZC", "CZXCXZCZXCXZC", "CXCXZCZXCZXCZX", "CZXCXZCXZCZXCCXZCZXC"));

    }

    private void initTable() {

        final Column<String> rankColumn = new Column<>("Thứ hạng", "rank", new TextImageDrawFormat<String>(50,50,1,25) {
            @Override
            protected Context getContext() {
                return HomeFragment.this.getContext();
            }

            @Override
            protected int getResourceID(String s, String value, int position) {
                switch (s){
                    case "\n":
                        return R.drawable.medal1;
                    case "\n\n":
                        return R.drawable.medal2;
                    case "\n\n\n":
                        return R.drawable.medal3;
                }
                return 0;
            }
        });
        final Column<String> nameColumn = new Column<>("Họ tên", "name1");
        final Column<String> ageColumn = new Column<>("Họ tên 2", "name2");
        final Column<String> addressColumn = new Column<>("Họ tên 3", "name3");
        final Column<String> phoneColumn = new Column<>("Họ tên 4", "name4");
        final Column<String> emailColumn = new Column<>("Họ tên 5", "name5");
        final Column<String> passwordColumn = new Column<>("Họ tên 6", "name6");
        final Column<String> heightColumn = new Column<>("Họ tên 7", "name7");
        final Column<String> heightColumn2 = new Column<>("Họ tên 8", "name8");

        tableData = new TableData<UserModel>("az",
                listUser, rankColumn, nameColumn, ageColumn, addressColumn, phoneColumn, emailColumn, passwordColumn, heightColumn,heightColumn2);
        smartTable.setTableData(tableData);
        smartTable.getConfig().setShowTableTitle(false);
        smartTable.getConfig().setShowXSequence(false);
        smartTable.getConfig().setShowYSequence(false);
        smartTable.getConfig().setFixedTitle(true);
        rankColumn.setFixed(true);
        nameColumn.setFixed(true);
        nameColumn.setWidth(250);
//        rankColumn.setWidth(50);
        ICellBackgroundFormat<CellInfo> backgroundFormat = new BaseCellBackgroundFormat<CellInfo>() {
            @Override
            public int getBackGroundColor(CellInfo cellInfo) {
                if (cellInfo.row % 2 == 0) {
                    return ContextCompat.getColor(getContext(), R.color.content_bg);
                }
                return TableConfig.INVALID_COLOR;

            }
        };
        smartTable.getConfig().setContentCellBackgroundFormat(backgroundFormat);
        smartTable.getConfig().setVerticalPadding(0);
        smartTable.getConfig().setColumnTitleBackground
                (new BaseBackgroundFormat(getResources().getColor(R.color.color_header_table)));
        smartTable.getConfig().setColumnTitleStyle(new FontStyle(getContext(), 13, ContextCompat.getColor(getContext(), R.color.white)));
        smartTable.getConfig().setColumnTitleVerticalPadding(24);
        smartTable.getConfig().setColumnTitleHorizontalPadding(0);
    }
}
