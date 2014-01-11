package org.xslife.LocalChurch.download;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.net.HttpURLConnection;  
import java.net.URL;  
  
import android.app.Notification;  
import android.app.NotificationManager;  
import android.app.PendingIntent;  
import android.content.Context;  
import android.content.Intent;  
import android.net.Uri;  
import android.os.Environment;  
import android.os.Handler;  
import android.os.Message;  
import android.util.Log;  
import android.widget.RemoteViews;  
import android.widget.Toast;  
   
  
/** 
 * Android����֮�ļ����أ�״̬ʱ��ʾ���ؽ��ȣ�����Զ���װ  http://blog.csdn.net/ahutzh/article/details/6910765
 * <dl> 
 * <dt>AppFileDownUtils.java</dt> 
 * <dd>Description: �ļ�����</dd> 
 * <dd>Copyright: Copyright (C) 2011</dd> 
 * <dd>Company: </dd> 
 * <dd>CreateDate: 2011-10-19</dd> 
 * </dl> 
 *  
 * @author ZhanHua 
 */  
public class AppFileDownUtils extends Thread {  
  
//    private Context mContext;  
//    private Handler mHandler;  
//    private String mDownloadUrl; // �ļ�����url�������ǿռ��  
//    private String mFileName;  
//    private Message msg;  
//  
//    private final String APP_FOLDER = "DownDemo"; // sd��Ӧ��Ŀ¼  
//    private final String APK_FOLDER = "apkFile"; // ����apk�ļ�Ŀ¼  
//  
//    public static final int MSG_UNDOWN = 0; //δ��ʼ����  
//    public static final int MSG_DOWNING = 1; // ������  
//    public static final int MSG_FINISH = 1; // �������  
//    public static final int MSG_FAILURE = 2;// ����ʧ��  
//  
//    private NotificationManager mNotifManager;  
//    private Notification mDownNotification;  
//    private RemoteViews mContentView; // ���ؽ���View  
//    private PendingIntent mDownPendingIntent;  
//  
//    public AppFileDownUtils(Context context, Handler handler, String downloadUrl, String fileName) {  
//        mContext = context;  
//        mHandler = handler;  
//        mDownloadUrl = downloadUrl;  
//        mFileName = fileName;  
//        mNotifManager = (NotificationManager) mContext  
//                .getSystemService(Context.NOTIFICATION_SERVICE);  
//        msg = new Message();  
//    }  
//  
//    @Override  
//    public void run() {  
//        try {  
//            if (Environment.getExternalStorageState().equals(  
//                    Environment.MEDIA_MOUNTED)) {  
//                Message downingMsg = new Message();  
//                downingMsg.what = MSG_DOWNING;  
//                mHandler.sendMessage(downingMsg);  
//                // SD��׼����  
//                File sdcardDir = Environment.getExternalStorageDirectory();  
//                // �ļ����·���� sdcard/DownDemo/apkFile  
//                File folder = new File(sdcardDir + File.separator + APP_FOLDER  
//                        + File.separator + APK_FOLDER);  
//                if (!folder.exists()) {  
//                    //�������Ŀ¼  
//                    folder.mkdir();  
//                }  
//                File saveFilePath = new File(folder, mFileName);  
//                System.out.println(saveFilePath);  
//                mDownNotification = new Notification(  
//                        android.R.drawable.stat_sys_download, mContext  
//                                .getString(R.string.notif_down_file), System  
//                                .currentTimeMillis());  
//                mDownNotification.flags = Notification.FLAG_ONGOING_EVENT;  
//                mDownNotification.flags = Notification.FLAG_AUTO_CANCEL;  
//                mContentView = new RemoteViews(mContext.getPackageName(),  
//                        R.layout.custom_notification);  
//                mContentView.setImageViewResource(R.id.downLoadIcon,  
//                        android.R.drawable.stat_sys_download);  
//                mDownPendingIntent = PendingIntent.getActivity(mContext, 0, new Intent(), 0);  
//                boolean downSuc = downloadFile(mDownloadUrl, saveFilePath);  
//                if (downSuc) {  
//                    msg.what = MSG_FINISH;  
//                    Notification notification = new Notification(  
//                            android.R.drawable.stat_sys_download_done, mContext  
//                                    .getString(R.string.downloadSuccess),  
//                            System.currentTimeMillis());  
//                    notification.flags = Notification.FLAG_ONGOING_EVENT;  
//                    notification.flags = Notification.FLAG_AUTO_CANCEL;  
//                    Intent intent = new Intent(Intent.ACTION_VIEW);  
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
//                    intent.setDataAndType(Uri.fromFile(saveFilePath),  
//                            "application/vnd.android.package-archive");  
//                    PendingIntent contentIntent = PendingIntent.getActivity(  
//                            mContext, 0, intent, 0);  
//                    notification.setLatestEventInfo(mContext, mContext  
//                            .getString(R.string.downloadSuccess), null,  
//                            contentIntent);  
//                    mNotifManager.notify(R.drawable.icon, notification);  
//                } else {  
//                    msg.what = MSG_FAILURE;  
//                    Notification notification = new Notification(  
//                            android.R.drawable.stat_sys_download_done, mContext  
//                                    .getString(R.string.downloadFailure),  
//                            System.currentTimeMillis());  
//                    notification.flags = Notification.FLAG_AUTO_CANCEL;  
//                    PendingIntent contentIntent = PendingIntent.getActivity(  
//                            mContext, 0, new Intent(), 0);  
//                    notification.setLatestEventInfo(mContext, mContext  
//                            .getString(R.string.downloadFailure), null,  
//                            contentIntent);  
//                    mNotifManager.notify(R.drawable.icon, notification);  
//                }  
//  
//            } else {  
//                Toast.makeText(mContext, Environment.getExternalStorageState(),  
//                        Toast.LENGTH_SHORT).show();  
//                msg.what = MSG_FAILURE;  
//            }  
//        } catch (Exception e) {  
//            Log.e(AhzyFisActivity.TAG, "AppFileDownUtils catch Exception:", e);  
//            msg.what = MSG_FAILURE;  
//        } finally {  
//            mHandler.sendMessage(msg);  
//        }  
//    }  
//  
//    /** 
//     *  
//     * Desc:�ļ����� 
//     *  
//     * @param downloadUrl 
//     *            ����URL 
//     * @param saveFilePath 
//     *            �����ļ�·�� 
//     * @return ture:���سɹ� false:����ʧ�� 
//     */  
//    public boolean downloadFile(String downloadUrl, File saveFilePath) {  
//        int fileSize = -1;  
//        int downFileSize = 0;  
//        boolean result = false;  
//        int progress = 0;  
//        try {  
//            URL url = new URL(downloadUrl);  
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
//            if (null == conn) {  
//                return false;  
//            }  
//            // ��ȡ��ʱʱ�� ���뼶  
//            conn.setReadTimeout(10000);  
//            conn.setRequestMethod("GET");  
//            conn.setDoInput(true);  
//            conn.connect();  
//            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {  
//                fileSize = conn.getContentLength();  
//                InputStream is = conn.getInputStream();  
//                FileOutputStream fos = new FileOutputStream(saveFilePath);  
//                byte[] buffer = new byte[1024];  
//                int i = 0;  
//                int tempProgress = -1;  
//                while ((i = is.read(buffer)) != -1) {  
//                    downFileSize = downFileSize + i;  
//                    // ���ؽ���  
//                    progress = (int) (downFileSize * 100.0 / fileSize);  
//                    fos.write(buffer, 0, i);  
//  
//                    synchronized (this) {  
//                        if (downFileSize == fileSize) {  
//                            // �������  
//                            mNotifManager.cancel(R.id.downLoadIcon);  
//                        } else if (tempProgress != progress) {  
//                            // ���ؽ��ȷ����ı䣬����Message  
//                            mContentView.setTextViewText(R.id.progressPercent,  
//                                    progress + "%");  
//                            mContentView.setProgressBar(R.id.downLoadProgress,  
//                                    100, progress, false);  
//                            mDownNotification.contentView = mContentView;  
//                            mDownNotification.contentIntent = mDownPendingIntent;  
//                            mNotifManager.notify(R.id.downLoadIcon,  
//                                    mDownNotification);  
//                            tempProgress = progress;  
//                        }  
//                    }  
//                }  
//                fos.flush();  
//                fos.close();  
//                is.close();  
//                result = true;  
//            } else {  
//                result = false;  
//            }  
//        } catch (Exception e) {  
//            result = false;  
//            Log.e(AhzyFisActivity.TAG, "downloadFile catch Exception:", e);  
//        }  
//        return result;  
//    }  
} 
