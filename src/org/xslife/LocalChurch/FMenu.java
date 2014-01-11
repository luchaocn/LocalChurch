package org.xslife.LocalChurch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


//public class android.support.v4.app.ListFragment extends android.support.v4.app.Fragment
public class FMenu extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//String[] colors = getResources().getStringArray(R.array.color_names);
		String[] colors = {"church list","book list","map","X","XX"};
		ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, android.R.id.text1, colors);
		setListAdapter(colorAdapter);

	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {

		Fragment newContent = null;
		switch (position) {
		case 0:
			newContent = new FChurchList();
			break;
		case 1:
			newContent = new FBookList();
			break;
		case 2:
			showMap();
			break;			
			
		}
		if (newContent != null)
			switchFragment(newContent);

	}
	private void showMap(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), AMap.class);
        //intent.putExtra("index", index);
        startActivity(intent);
	}

	// the meat of switching the above fragment. be called in ColorMenuFragment.onListItemClick()
	private void switchFragment(Fragment fragment) {

		// FragmentActivity android.support.v4.app.Fragment.getActivity()
		if (getActivity() == null)
			return;

		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchContent(fragment);
		}

	}
}

