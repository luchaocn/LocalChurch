package org.xslife.LocalChurch;

import java.io.Serializable;
import java.util.List;

public class Book {

	//private NewsType newType;
	//private List<Relative> relatives;	
	
	//public final static String UTF8 = "UTF-8";
	//public final static String NODE_ROOT = "oschina";
	public final static String NODE_START = "book";
	public final static String NODE_END = "book";
	public final static String NODE_ID = "bookid";
	public final static String NODE_TITLE = "booktitle";
	public final static String NODE_SUBDIR = "subdir";
	public final static String NODE_AUTHOR = "author";
	public int mId;
	public String mTitle;
	public String mCategory;
	public String mAuthor;
	public String mRemoteUrl;
	public String mLocalUrl;
	public int mDownloadPgrgress;
	public Book(){
	}
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		mId = id;
	}
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String title) {
		mTitle = title;
	}
	public String getCategory() {
		return mCategory;
	}
	public void setCategory(String category) {
		mCategory = category;
	}
	public String getAuthor() {
		return mAuthor;
	}
	public void setAuthor(String author) {
		mAuthor = author;
	}


	
}
