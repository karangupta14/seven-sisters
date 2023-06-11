package com.example.android.sevensisters;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.android.sevensisters.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        extras = getIntent().getExtras();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String state = extras.getString("state");
        LatLng latLng = new LatLng(26, 92);
        if(state.equals("meghalaya")){
            latLng = new LatLng(25, 90);
        }
        if(state.equals("tripura")){
            latLng = new LatLng(23, 91);
        }
        if(state.equals("mizoram")){
            latLng = new LatLng(21, 92);
        }
        if(state.equals("arunachal")){
            latLng = new LatLng(28, 95);
        }
        if(state.equals("manipur")){
            latLng = new LatLng(24, 93);
        }
        if(state.equals("nagaland")){
            latLng = new LatLng(26, 94);
        }
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.setMinZoomPreference(12);
        mMap.setMaxZoomPreference(14);

    }
}