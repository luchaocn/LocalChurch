package org.xslife.LocalChurch.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static String DATABASE_NAME="down.db";
	private static int version=2;

	public DBHelper(Context context)  {
		super(context, DATABASE_NAME, null, version);
	}

    /**
     * Called when the database is created for the first time. This is where the creation of tables and the initial population of the tables should happen.
     * 在down.db数据库下创建一个download_info表存储下载信息
     * 创建一个localdown_info表存储本地下载信息
     */
	@Override
	public void onCreate(SQLiteDatabase db) {
		 // downloading file info
		 db.execSQL("create table thread_tab(_id integer PRIMARY KEY AUTOINCREMENT,"
	                + "thread_id integer,  start_pos integer, end_pos integer, compelete_size integer, url varchar(50))");
		 // downloaded file info. include fileName, URL, and fileSize.
		 db.execSQL("create table localfile_tab(_id integer PRIMARY KEY AUTOINCREMENT,file_name varchar(30),url varchar(50),fileSize integer)");
	}

	/*
	 * Called when the database needs to be upgraded. The implementation should use this method to drop tables, add tables, or do anything else it needs to upgrade to the new schema version.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		String sql=   "drop table if exists thread_tab";
		String sqlOne="drop table if exists localfile_tab";
		db.execSQL(sql);
		db.execSQL(sqlOne);
		onCreate(db);
	}
}
