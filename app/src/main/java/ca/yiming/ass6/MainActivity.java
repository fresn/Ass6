package ca.yiming.ass6;

import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ca.yiming.ass6.dummy.PlaceContent;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, PlaceFragment.OnListFragmentInteractionListener {

    GoogleMap map;
    DialogFragment newFragment;
    LatLng homePlace, schoolPlace, tanPlace;
    TabLayout tabLayout;

    private static final String TAG = "MainActivity";

    MarkerOptions home, school, tan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        //maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        newFragment = PlaceFragment.newInstance(1);
        //tabs
        tabLayout =findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: "+tab.getPosition());
                switch (tab.getPosition()){
                    case 0:  map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    break;
                    case 1:  map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    break;
                    case 2:   map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });


    }


    public void onMapReady(GoogleMap googleMap) {
        this.map=googleMap;
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        homePlace = new LatLng(49.7976544,-97.1635298);
        schoolPlace = new LatLng(49.9001874,-97.1415618);
        tanPlace = new LatLng(39.9087243,116.3969327);


        home = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_home)).position(homePlace);
        school = new MarkerOptions().position(schoolPlace).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_school));
        tan=new MarkerOptions().position(tanPlace).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_tan));

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        googleMap.addMarker(home);
        googleMap.addMarker(school);
        googleMap.addMarker(tan);



        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(homePlace,15));






        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.


    }


    void showDialog() {

        //show the dialog.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        newFragment.show(fragmentTransaction, "dialog");
    }


    @Override
    public void onListFragmentInteraction(PlaceContent.PlaceItem item) {
        Log.d(TAG, "onListFragmentInteraction: " + item.id);
        newFragment.dismiss();
        Log.d(TAG, "onListFragmentInteraction: ");
        switch (item.id){
            case "1":this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(this.homePlace,15));

//                this.map.animateCamera(CameraUpdateFactory.zoomTo(15));
            break;
            case "2":this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(this.schoolPlace,15));

//                this.map.animateCamera(CameraUpdateFactory.zoomTo(15));
            break;
            case "3":this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(this.tanPlace,15));

//                this.map.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
    }
//    private void getLocationPermission() {
//        /*
//         * Request location permission, so that we can get the location of the
//         * device. The result of the permission request is handled by a callback,
//         * onRequestPermissionsResult.
//         */
//        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            mLocationPermissionGranted = true;
//        } else {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String permissions[],
//                                           @NonNull int[] grantResults) {
//        mLocationPermissionGranted = false;
//        switch (requestCode) {
//            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    mLocationPermissionGranted = true;
//                }
//            }
//        }
//        updateLocationUI();
//    }

}
