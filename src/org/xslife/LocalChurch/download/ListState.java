package org.xslife.LocalChurch.download;

import java.util.HashMap;
import java.util.Map;

public class ListState {
	
	/**
	 * 存放每个下载文件完成的下載狀�?
	 */
	public static Map<String,Integer>  state = new HashMap<String, Integer>();
	/**
	 * 存放每个下载文件完成的长�?
	 */
	public static Map<String,Integer> completeSizes=new HashMap<String, Integer>();
	/**
	 * 存放每个下载文件的�?长度
	 */
	public static Map<String,Integer> fileSizes=new HashMap<String, Integer>();
	
}
