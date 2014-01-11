package org.xslife.LocalChurch;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

//public class FMap extends Fragment {
public class AMap extends Activity {
    private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);
    private static final LatLng PERTH = new LatLng(-31.95285, 115.85734);

    private static final LatLng LHR = new LatLng(51.471547, -0.460052);
    private static final LatLng LAX = new LatLng(33.936524, -118.377686);
    private static final LatLng JFK = new LatLng(40.641051, -73.777485);
    private static final LatLng AKL = new LatLng(-37.006254, 174.783018);

    private static final int WIDTH_MAX = 50;
    private static final int HUE_MAX = 360;
    private static final int ALPHA_MAX = 255;

	String text = null;
	private GoogleMap mMap;

	// 1.
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.map);
    }

    // 2.
    protected void onStart(){
    	super.onStart();
    }
    
    // 3.
    protected void onRestart(){
    	super.onRestart();
    }

    // 4.
    protected void onResume(){
    	super.onResume();
    }

    // 5.
    protected void onPause(){
    	super.onPause();
    }

    // 6.
    protected void onStop(){
    	super.onStop();
    }

    // 7.
    protected void onDestroy(){
    	super.onDestroy();
    }

/*
    private void initFragment(){
 		// set the Above View
 		//if (savedInstanceState != null)
 		//	mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
 		//if (mContent == null)
 		mContent = new FChurchList();//new MapFragment();

 		// 1. ColorFragment -> Fragment. set the Above View
 		setContentView(R.layout.content_frame); //<FrameLayout
 		//FragmentManager
 		getSupportFragmentManager()
 			.beginTransaction()
 			.replace(R.id.content_frame, mContent)
 			.commit();

 		// 2. ColorMenuFragment -> ListFragment.  set the Behind View
 		setBehindContentView(R.layout.menu_frame); //<FrameLayout
 		//FragmentManager  FragmentTransaction
 		getSupportFragmentManager()
 			.beginTransaction()
 			.replace(R.id.menu_frame, new FMenu())
 			.commit();

 		// 3. Customize the SlidingMenu
 		SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidth(50);
        //sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffset(120);
        sm.setFadeDegree(0.35f);
        //设置slding menu的几种手势模式
        //TOUCHMODE_FULLSCREEN 全屏模式，在content页面中，滑动，可以打开sliding menu
        //TOUCHMODE_MARGIN 边缘模式，在content页面中，如果想打开slding ,你需要在屏幕边缘滑动才可以打开slding menu
        //TOUCHMODE_NONE 自然是不能通过手势打开啦
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);    	
    }
*/
/*	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.map, null);
		//setUpMapIfNeeded();
		return view;
		
		//if (savedInstanceState != null)
			//mColorRes = savedInstanceState.getInt("mColorRes");
		//int color = getResources().getColor(mColorRes);
		// construct the RelativeLayout
		RelativeLayout v = new RelativeLayout(getActivity());
		v.setBackgroundColor(R.color.green);		
		return v;
		
	}
*/
/*
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
//## Try to obtain the map from the SupportMapFragment.
        	//
        	  <fragment
			    android:id="@+id/fragmentmap"
			    android:layout_width="match_parent"
			    android:layout_height="match_parent"
			    class="com.google.android.gms.maps.SupportMapFragment"/>
        	//
            //mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentmap)).getMap();
            mMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.fragmentmap)).getMap();
            // Check if we were successful in obtaining the map.
            //if (mMap != null) {
            //    setUpMap();
            //}

            LatLng myDorm = new LatLng(37.412782,-121.863391);
            LatLng myPos = new LatLng(37.417205,-121.873127);

            mMap.setMyLocationEnabled(true);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myDorm, 13));

            // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
            //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            mMap.addMarker(new MarkerOptions()
	            .title("Sydney")
	            .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
	            .snippet("The most populous city in Australia.")
	            .position(myDorm));

            mMap.addMarker(new MarkerOptions()
	            .title("adfasdf")
	            .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
	            .snippet("The most populous 33333 in 3333.")
	            .position(myPos));

            // Polylines are useful for marking paths and routes on the map.
            mMap.addPolyline(new PolylineOptions().geodesic(true)
                    .add(new LatLng(-33.866, 151.195))  // Sydney
                    .add(new LatLng(-18.142, 178.431))  // Fiji
                    .add(new LatLng(21.291, -157.821))  // Hawaii
                    .add(new LatLng(37.423, -122.091))  // Mountain View
            );  
            
            
            //setUpMap();
        }
    }
*/
    /*
    private void setUpMap() {
 
        // A simple polyline with the default options from Melbourne-Adelaide-Perth.
        mMap.addPolyline((new PolylineOptions()).add(MELBOURNE, ADELAIDE, PERTH));

        // A geodesic polyline that goes around the world.
        mMap.addPolyline((new PolylineOptions())
                .add(LHR, AKL, LAX, JFK, LHR)
                .width(5)
                .color(Color.BLUE)
                .geodesic(true));

        // Rectangle centered at Sydney.  This polyline will be mutable.
        int radius = 5;
        PolylineOptions options = new PolylineOptions()
                .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude + radius))
                .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude - radius))
                .add(new LatLng(SYDNEY.latitude - radius, SYDNEY.longitude - radius))
                .add(new LatLng(SYDNEY.latitude - radius, SYDNEY.longitude + radius))
                .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude + radius));
        int color = Color.HSVToColor( mAlphaBar.getProgress(), new float[] {mColorBar.getProgress(), 1, 1});
        mMutablePolyline = mMap.addPolyline(options
                .color(color)
                .width(mWidthBar.getProgress()));

        mColorBar.setOnSeekBarChangeListener(this);
        mAlphaBar.setOnSeekBarChangeListener(this);
        mWidthBar.setOnSeekBarChangeListener(this);

        // Move the map so that it is centered on the mutable polyline.
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));
    }*/
}