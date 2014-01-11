package org.xslife.LocalChurch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xslife.LocalChurch.download.DownloadService;

import org.xslife.LocalChurch.download.FileState;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class FBookList extends Fragment {
	private List<Book> mBookInfoList;
	private MyAdapter mBookListAdapter;
	Context mContext; 
	
	DownloadManagerReceiver mDMReceiver;
	//private List<Map<String, Object>> mBookInfoList;
	String text = null;
	private static final String[] COUNTRIES = new String[] { "shengjing1", "madingf", "shige", "shengyan" };

	// 1.1.
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	// 1.2.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mContext = this.getActivity();
	}

	// 1.3.
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mDMReceiver = new DownloadManagerReceiver();
		
		// inflater the layout
		View view = inflater.inflate(R.layout.book, null);
		ListView lvBook = (ListView) view.findViewById(R.id.lvRemoteBook);

		// 1.ArrayAdapter
		// ArrayAdapter aaChurch = new ArrayAdapter<String>(getActivity(),
		// android.R.layout.simple_list_item_1, getData());
		// lvBook.setAdapter(aaChurch);

		// 3.
		// SimpleAdapter adapter = new SimpleAdapter( this.getActivity(),
		// getBookData(),R.layout.booklistitem,
		// new String[]{"title","info","img"}, new
		// int[]{R.id.booktitle,R.id.bookinfo,R.id.bookcover});
		// lvBook.setAdapter(adapter);

		mBookInfoList = getBookData();
		
		mBookListAdapter = new MyAdapter(this.getActivity());
		lvBook.setAdapter(mBookListAdapter);

		return view;
	}

	@SuppressWarnings("finally")
	private List<Book> getBookData() {
//		List<FileState> list = new ArrayList<FileState>();
/*
		FileState book = new FileState();
		book.setFileName("bible-en-cn.pdb");
		book.setCompleteSize(0);
		list.add(book);

		FileState fs2 = new FileState();
		fs2.setFileName("lifeStudy/BOOK.pdb");
		fs2.setCompleteSize(0);
		list.add(fs2);
*/
		try{
			String path = "/mnt/sdcard/1localDown/booklist.xml";
			FileInputStream inputStream = new FileInputStream(path);	
			BookListMgr bookListMgr = BookListMgr.Parse(inputStream);
			return bookListMgr.getBookList();
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		finally
		{
		}
	}

	// 1.4.
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	// 1.5.
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
	}

	// 1.6.
	@Override
	public void onStart() {

		super.onStart();
	}

	// 1.7.
	@Override
	public void onResume() {
		mContext.registerReceiver(mDMReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		super.onResume();
	}

	// 2.1.
	@Override
	public void onPause() {

		super.onPause();
	}

	// 2.2.
	@Override
	public void onStop() {
		if(mDMReceiver != null)
			mContext.unregisterReceiver(mDMReceiver);
		super.onStop();
	}

	// 2.3.
	public void onDestroyView() {
		super.onDestroyView();
	}

	// 2.4.
	@Override
	public void onDestroy() {

		super.onDestroy();
	}

	// 2.5.
	@Override
	public void onDetach() {

		super.onDetach();
	}

	class UpdateReceiver extends BroadcastReceiver {
		private Context context;

		public UpdateReceiver(Context context) {
			this.context = context;
		}

		public void registerAction(String action) {
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(action);
			FBookList.this.getActivity().registerReceiver(this, intentFilter);
		}

		@Override
		public void onReceive(Context context, Intent intent) {
			// 接收来自DownloadService传送过来的数据,并且更新进度条
			if (intent.getAction().equals("updateUI")) {
				String url = intent.getStringExtra("url");
				int completeSize = intent.getIntExtra("completeSize", 0);
				for (int i = 0; i < mBookInfoList.size(); i++) {
					Book fileState = mBookInfoList.get(i);
					if (fileState.mRemoteUrl.equals(url)) {
						fileState.mDownloadPgrgress = completeSize;
						mBookInfoList.set(i, fileState);// 更新list中的数据
						break;
					}
				}
				//mBookListAdapter.setData(mBookInfoList);// 更新完list的数据要把它重新传入adapter，这样才能更新
				mBookListAdapter.notifyDataSetChanged();
			}
		}
	}

	// this adapter for book ListView, book url and download button
	public class MyAdapter extends BaseAdapter {
		// private List<Map<String, Object>> mBookInfoList;
		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if( null != mBookInfoList )
				return mBookInfoList.size();
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		public final class ViewHolder {
			public ImageView img;
			public TextView title;
			public TextView info;
			public Button btnDownload;
			public Button btnDel;
			public ProgressBar pregress;
			public Book book;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final int listItemPos = position;
			Book book = mBookInfoList.get(position); //FileState
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.booklistitem, null);
				holder.img 			= (ImageView) convertView.findViewById(R.id.bookcover);
				holder.title 		= (TextView) convertView.findViewById(R.id.booktitle);
				holder.info 		= (TextView) convertView.findViewById(R.id.bookinfo);
				holder.btnDownload 	= (Button) convertView.findViewById(R.id.btnDLBook);
				holder.btnDownload.setTag(book);
				holder.btnDel		= (Button) convertView.findViewById(R.id.btnDelBook);
				holder.btnDel.setTag(book);
				holder.pregress		= (ProgressBar) convertView.findViewById(R.id.doanloadProgress);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			//holder.img.setBackgroundResource((Integer) mBookInfoList.get(position).get("img"));
			String title = book.getTitle();
			holder.title.setText(title);
			//holder.info.setText((String) mBookInfoList.get(position).get("info"));
			holder.pregress.setProgress(book.mDownloadPgrgress ); //#####

			final String remoteUrl = book.mRemoteUrl;
			final String localUrl = book.mLocalUrl;

			// click download button
			holder.btnDownload.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// 得到下载按鈕的父控件,也就是item的Linearlayou布局
					//LinearLayout layout = (LinearLayout) v.getParent();
					// 得到所点击Item上的TextView
					//TextView fileName = (TextView) layout.findViewById(R.id.network_filename);
					
					Book book = (Book) v.getTag();
					String remoteUrl = book.mRemoteUrl; //"http://xslife.org/localchurch/bible-en-cn.pdb"; // "bible-en-cn.pdb"; // 
					startDownload(remoteUrl,URLS.LOCAL_DIR,book.mTitle );
					book.mLocalUrl = "file://mnt/sdcard/1localDown/"+book.mTitle;
					return;
				}
			});

			// click delete button
			holder.btnDel.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Book book = (Book) v.getTag();
					if(null == book) return;
					//String remoteUrl = book.mLocalUrl; //"http://xslife.org/localchurch/bible-en-cn.pdb"; // "bible-en-cn.pdb"; // 

					
					// 1.
					//Intent intent = new Intent();
					//ComponentName component = new ComponentName("com.dcco.app.iSilo","com.dcco.app.iSilo.iSiloActivityGroup");
					//intent.setComponent(component);
					//Uri data = Uri.parse("file://mnt/sdcard/1localDown/01_Genesis.pdb");
					//intent.setData(data);
					//FBookList.this.getActivity().startActivity(intent);
					
					// 2.
					//Intent intent = new Intent();
					//intent.setAction("android.intent.category.BROWSABLE");
					//FBookList.this.getActivity().startActivity(intent);

					// 3.
					//Uri data = Uri.parse("http://xslife.org/localchurch/book/lifeStudy/01_Genesis.pdb");
					Uri data = Uri.parse("file://"+ book.mLocalUrl );//;"file://mnt/sdcard/1localDown/01_Genesis.pdb"
					Intent intent = new Intent();
					intent.setAction("android.intent.action.VIEW");
					intent.setPackage("com.dcco.app.iSilo");
					intent.setData(data);
					startActivity(intent);
					
					/*
					Intent intent = new Intent();
					//intent.putExtra("fileName", bookName);
					intent.putExtra("flag", "delete");
					intent.setClass(FBookList.this.getActivity(), DownloadService.class);
					FBookList.this.getActivity().startService(intent);
					mBookInfoList.remove(listItemPos);// remove掉List中相應數據
					// 刪除本地文件
					File file = new File(localUrl);
					if (file.delete()) {
						Toast.makeText(FBookList.this.getActivity(),URLS.LOCAL_DIR, Toast.LENGTH_SHORT).show();
					}
					notifyDataSetChanged();
					*/
				}
			});

			return convertView;
		}
	}

	public void startDownload(String fileRemoteURL,String localDir, String fileName) {

		// 1. DownloadManager[DownloadManagerReceiver,registerReceiver,unregisterReceiver]
		// minSdkVersion = 9
		// 其中在设置 down.setShowRunningNotification(false);时，需要添加相应的权限：		<uses-permission android:name="android.permission.DOWNLOAD_WITHOUT_NOTIFICATION" />
		DownloadManager downloadMgr = (DownloadManager)mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request dlReq=new DownloadManager.Request (Uri.parse(fileRemoteURL));  
        dlReq.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI);  
        dlReq.setShowRunningNotification(true);  
        dlReq.setVisibleInDownloadsUi(true);  
        //dlReq.setDestinationInExternalFilesDir(mContext, URLS.LOCAL_DIR, "qqinput.apk");  
        
        dlReq.setDestinationInExternalPublicDir( localDir, fileName);
        //将下载请求放入队列  
        long lDownloadID = downloadMgr.enqueue(dlReq);  		

		// 2. use new service
//		Intent intent = new Intent();
//		intent.setClass(this.getActivity(), DownloadService.class);
//		intent.putExtra("fileName", strFileRemoteURL);
//		intent.putExtra("flag", "startDownload");
//		this.getActivity().startService(intent);// 点击下载按钮，启动service进行下载
	}
    class DownloadManagerReceiver extends BroadcastReceiver {  
        @Override  
        public void onReceive(Context context, Intent intent) {  
        	
            if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)){  
                long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);  
                Toast.makeText(context, intent.getAction()+"id : "+downId, Toast.LENGTH_SHORT).show();  
            } 
        }
    }
    //使用Android自带DownloadManager下载文件 http://blog.csdn.net/lixiang0522/article/details/7608310
//    private void queryDownloadStatus(long lDownloadID) {   
//        DownloadManager.Query query = new DownloadManager.Query();   
//        query.setFilterById(prefs.getLong(DL_ID, 0));   
//        Cursor c = downloadManager.query(query);   
//        if(c.moveToFirst()) {   
//            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));   
//            switch(status) {   
//            case DownloadManager.STATUS_PAUSED:   
//                Log.v("down", "STATUS_PAUSED");  
//            case DownloadManager.STATUS_PENDING:   
//                Log.v("down", "STATUS_PENDING");  
//            case DownloadManager.STATUS_RUNNING:   
//                //正在下载，不做任何事情  
//                Log.v("down", "STATUS_RUNNING");  
//                break;   
//            case DownloadManager.STATUS_SUCCESSFUL:   
//                //完成  
//                Log.v("down", "下载完成");  
//                break;   
//            case DownloadManager.STATUS_FAILED:   
//                //清除已下载的内容，重新下载  
//                Log.v("down", "STATUS_FAILED");  
//                downloadManager.remove(prefs.getLong(DL_ID, 0));   
//                prefs.edit().clear().commit();   
//                break;   
//            }   
//        }  
//    }
//	public String encodeGB(String string) {
//		// 转换中文编码
//		String split[] = string.split("/");
//		for (int i = 1; i < split.length; i++) {
//			try {
//				split[i] = URLEncoder.encode(split[i], "GB2312");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			split[0] = split[0] + "/" + split[i];
//		}
//		split[0] = split[0].replaceAll("\\+", "%20");// 处理空格
//		return split[0];
//	} 
    
	//
	public void showInfo() {
		new AlertDialog.Builder(this.getActivity()).setTitle("我的listview").setMessage("介绍...").setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		}).show();
	}

}
