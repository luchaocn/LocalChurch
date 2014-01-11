package org.xslife.LocalChurch;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xslife.LocalChurch.Book;
import android.util.Xml;

// Activity
public class BookListMgr implements Serializable {
	//public final static int CATALOG_ALL = 1;
	// ArrayList is an implementation of List, backed by an array
	private List<Book> mBookList = new ArrayList<Book>();
	public int mBookQuantiry;
	public String mBookRootUrl;
	public final static String ENCODING = "UTF-8";
	//public final static String NODE_ROOT = "oschina";	

	//public final static int CATALOG_ALL = 1;
	//public final static int CATALOG_INTEGRATION = 2;
	//public final static int CATALOG_SOFTWARE = 3;
	//private int catalog;
	//private int pageSize;
	//private int newsCount;

	public List<Book> getBookList() {
		return mBookList;
	}

	public static BookListMgr Parse(InputStream inputStream) throws IOException{//, AppException {
		BookListMgr bookListMgr = new BookListMgr();
		Book book = null;
		XmlPullParser xmlParser = Xml.newPullParser();
		try {
			xmlParser.setInput(inputStream, ENCODING);
			// START_TAG, END_TAG, TEXT END_DOCUMENT
			int evtType = xmlParser.getEventType();

			while (evtType != XmlPullParser.END_DOCUMENT) {
				String tag = xmlParser.getName();
				switch (evtType) {
				case XmlPullParser.START_TAG:
					if (       tag.equalsIgnoreCase("bookRootUrl")) {
						bookListMgr.mBookRootUrl = xmlParser.nextText();
					} else if (tag.equalsIgnoreCase("bookQuantiry")) {
						bookListMgr.mBookQuantiry = StringUtils.toInt(xmlParser.nextText(), 0);
					} else if (tag.equalsIgnoreCase(Book.NODE_START)) { // book
						book = new Book();
					} else if (book != null) {
						if (tag.equalsIgnoreCase(Book.NODE_ID)) { // bookId
							book.mId = StringUtils.toInt(xmlParser.nextText(), 0);
						} else if (tag.equalsIgnoreCase(Book.NODE_TITLE)) {
							book.setTitle(xmlParser.nextText());
							book.mRemoteUrl = bookListMgr.mBookRootUrl + "/lifeStudy/" + book.mTitle;
						} else if (tag.equalsIgnoreCase(Book.NODE_AUTHOR)) {
							book.setAuthor(xmlParser.nextText());
						} else if (tag.equalsIgnoreCase(Book.NODE_SUBDIR)) {
							//book.setAuthor(xmlParser.nextText());
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if (tag.equalsIgnoreCase(Book.NODE_END) && book != null) {
						bookListMgr.getBookList().add(book);
						book = null;
					}
					break;
				}
				int a = xmlParser.next();
				evtType = a;
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			//throw AppException.xml(e);
		} finally {
			inputStream.close();
		}
		return bookListMgr;
	}
}
