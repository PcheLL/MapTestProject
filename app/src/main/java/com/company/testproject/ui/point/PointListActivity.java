package com.company.testproject.ui.point;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.company.testproject.R;
import com.company.testproject.model.PointInfoDataList;
import com.company.testproject.presenter.point.IPointListPresenter;
import com.company.testproject.presenter.point.PointListPresenter;
import com.company.testproject.ui.adapter.PointListAdapter;
import com.company.testproject.ui.adapter.PointListAdapterListener;
import com.company.testproject.ui.map.MapActivity;
import com.company.testproject.utils.Const;

import java.util.List;

public class PointListActivity extends AppCompatActivity implements PointListView, PointListAdapterListener {
    private IPointListPresenter presenter;
    private RecyclerView dataInfoRecyclerView;
    private PointListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_list);
        dataInfoRecyclerView = findViewById(R.id.rv_list_point);
        init();
    }

    private void init() {
        presenter = new PointListPresenter(this, getIntent().getStringExtra(Const.ARGUMENT_USER_CODE));
        presenter.loadPointData();

        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new PointListAdapter(this, this);
        dataInfoRecyclerView.setAdapter(adapter);
        dataInfoRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void updatePointListAdapter(List<String> data) {
        adapter.updateDataList(data);
    }

    @Override
    public void showMapScreen(PointInfoDataList data) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(Const.ARGUMENT_USER_DATA, data);
        startActivity(intent);
    }

    @Override
    public void onPointListItemClicked(int position) {
        presenter.showMapScreen(position);
    }

    @Override
    public void loadNextPointData() {
        presenter.loadPointData();
    }

}