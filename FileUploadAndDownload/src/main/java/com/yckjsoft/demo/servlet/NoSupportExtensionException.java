package com.yckjsoft.demo.servlet;

/**
 * 不支持的扩展名异常类
 * 
 * @author chengqi
 *
 */
public class NoSupportExtensionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSupportExtensionException(String message) {
		super(message);
	}
}
