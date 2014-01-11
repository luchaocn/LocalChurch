package org.xslife.LocalChurch;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FChurchList extends Fragment {
    String text = null;
    private  static final String[] COUNTRIES=new String[]{"中国","俄罗斯","英国","法国"};

    public FChurchList() {
    }

    // 1.1.
    public void onAttach (Activity activity){
    	super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.e("LocalChurch", "onCreate:"+text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("LocalChurch", "onCreateView:"+ text);
        //inflater the layout 
        View view = inflater.inflate(R.layout.church, null);
        Button button =(Button)view.findViewById(R.id.button1);
        button.setText("button 1");
        
        ListView lvChurch = (ListView)view.findViewById(R.id.churchlist);
        
        ArrayAdapter aaChurch = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, COUNTRIES);
        lvChurch.setAdapter(aaChurch);
        
        return view;
    }
    private List<String> getData(){
        
        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
         
        return data;
    }
    
    
    
    // 1.4.
    public void onActivityCreated (Bundle savedInstanceState){
    	super.onActivityCreated(savedInstanceState);
    }
    
    // 1.5.
    public void onViewStateRestored (Bundle savedInstanceState){
    	super.onViewStateRestored(savedInstanceState);
    }

    // 1.6.
    @Override
    public void onStart() {
        Log.e("LocalChurch", "onStart:"+ text);
        super.onStart();
    }
    
    // 1.7.
    @Override
    public void onResume() {
        Log.e("LocalChurch", "onResume:"+ text);
        super.onResume();
    }

    // 2.1.
    @Override
    public void onPause() {
        Log.e("LocalChurch", "onPause:"+ text);
        super.onPause();
    }
    
    // 2.2.
    @Override
    public void onStop() {
    	super.onStart();
        Log.e("LocalChurch", "onStop:"+ text);
    }  
    
    // 2.3.
    public void onDestroyView (){
    	super.onDestroyView();
    }
    
    // 2.4.
    @Override
    public void onDestroy() {
        Log.e("LocalChurch", "onDestroy:"+ text);
        super.onDestroy();
    }

    // 2.5.
    @Override
    public void onDetach() {
        Log.e("LocalChurch", "onDetach:"+ text);
        super.onDetach();
    }

}
