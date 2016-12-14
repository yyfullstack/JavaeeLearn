package com.yckjsoft.demo.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文件上传进度获取Servlet
 * 
 * @author chengqi
 *
 */
public class GetFileProcessServlet extends HttpServlet {

	/** 日志对象*/
	private Log logger = LogFactory.getLog(this.getClass());

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("GetFileProcessServlet#doGet start");
		String fileUploadPercent = (String)request.getSession().getAttribute("fileUploadProcess");
		Writer writer = null;
		try {
			writer = response.getWriter();
			logger.info("percent:" + fileUploadPercent);
			IOUtils.write(fileUploadPercent == null ? "0%" : fileUploadPercent, writer);
			writer.flush();
			writer.close();
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
		logger.info("GetFileProcessServlet#doGet end");
	}

}
