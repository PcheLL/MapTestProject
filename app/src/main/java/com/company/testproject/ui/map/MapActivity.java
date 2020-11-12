package com.company.testproject.ui.map;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.company.testproject.R;
import com.company.testproject.model.PointInfoDataList;
import com.company.testproject.utils.Const;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private PointInfoDataList data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        LinearLayout previous = findViewById(R.id.ll_previous);
        previous.setOnClickListener(this);

        init();
    }

    private void init() {
        data = (PointInfoDataList) getIntent().getSerializableExtra(Const.ARGUMENT_USER_DATA);

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mapFragment);
        fragmentTransaction.commit();
        mapFragment.getMapAsync(this);
        initFields();
    }

    private void initFields() {
        TextView idTextView = findViewById(R.id.tv_id);
        idTextView.setText(data.getId());
        TextView countryTextView = findViewById(R.id.tv_country);
        countryTextView.setText(data.getCountry());
        TextView nameTextView = findViewById(R.id.tv_name);
        nameTextView.setText(data.getName());
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng marker = new LatLng(data.getLat(), data.getLon());
        map.addMarker(new MarkerOptions()
                .position(marker)
                .title(data.getName()));
        map.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
