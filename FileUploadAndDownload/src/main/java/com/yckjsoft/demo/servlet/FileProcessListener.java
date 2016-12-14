package com.yckjsoft.demo.servlet;

import java.text.NumberFormat;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文件进度监听器
 * 
 * @author chengqi
 *
 */
public class FileProcessListener implements ProgressListener{

	/** 日志对象*/
	private Log logger = LogFactory.getLog(this.getClass());

	private HttpSession session;

	public FileProcessListener(HttpSession session) {
		this.session = session;  
	}
	

	public void update(long pBytesRead, long pContentLength, int pItems) {
		double readByte = pBytesRead;
		double totalSize = pContentLength;
		if(pContentLength == -1) {
			logger.debug("item index[" + pItems + "] " + pBytesRead + " bytes have been read.");
		} else {
			logger.debug("item index[" + pItems + "] " + pBytesRead + " of " + pContentLength + " bytes have been read.");
			String p = NumberFormat.getPercentInstance().format(readByte / totalSize);
			session.setAttribute("fileUploadProcess", p);
		}
	}

}
