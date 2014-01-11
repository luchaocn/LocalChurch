package org.xslife.LocalChurch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import org.xslife.LocalChurch.AMap;
/**
 * This shows how to draw polylines on a map.
 */
//BaseActivity -> SlidingFragmentActivity -> SherlockFragmentActivity -> Watson -> FragmentActivity -> Activity
//                                        ->                                       FragmentActivity -> Activity
public class MainActivity extends  SlidingFragmentActivity implements OnSeekBarChangeListener {
//public class MainActivity extends FragmentActivity implements OnSeekBarChangeListener {
//public class MainActivity extends SlidingActivity implements OnSeekBarChangeListener {
//public class MainActivity extends Activity {

    private Fragment mContent;
    private Connection dbCon;

	public static Connection getConnection(){
		Connection con=null;
		try {
	      Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	      con = (Connection) DriverManager.getConnection( "jdbc:mysql://173.255.250.49:3306/localchurch","localchurch","LocalChurch" );
			           //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		  System.out.println("Success connect Mysql server!");
	    }catch( SQLException ee)  {
	    	ee.printStackTrace();
	    } catch (Exception e) {
	      System.out.print("Error loading Mysql Driver!"); 
	      e.printStackTrace(); 
	    }
	    return con;
	}

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        initFragment();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
    }

    private boolean getChurchInfo(){
    	return true;
    }

    
    private void initFragment(){
 		// set the Above View
 		//if (savedInstanceState != null)
 		//	mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
 		//if (mContent == null)
 		//mContent = new FChurchList();//new MapFragment();

 		// 1. ColorFragment -> Fragment. set the Above View
 		setContentView(R.layout.content_frame); //<FrameLayout
 		
 		// 解决android.os.NetworkOnMainThreadException
 		// http://my.eoe.cn/iceskysl/archive/4382.html
 		if (android.os.Build.VERSION.SDK_INT > 9) { // just workaround
 		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
 		    StrictMode.setThreadPolicy(policy);
 		}
 		
 		
 		//FragmentManager
 		getSupportFragmentManager()
 			.beginTransaction()
 			.replace(R.id.content_frame, new FBookList())
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
    private void initActivity(){
    	
    }
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame, fragment)
			.commit();
		getSlidingMenu().showContent();
	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
