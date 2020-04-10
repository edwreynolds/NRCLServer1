package com.eprworld.nrcl.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatUtil {

	private static final Logger logger = LoggerFactory.getLogger(FormatUtil.class);

	public final static String databaseDateFormat = "yyyy-MM-dd";
	public final static String databaseDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	private static String defaultIso8601DateTime = "YYYY-MM-dd HH:mm:ss";
	private static String defaultIso8601Date = "YYYY-MM-dd";
	private static String defaultIso8601Time = "HH:mm:ss";

	private static String systemDefaultDateTimeFormat = "MM/dd/yyyy HH:mm:ss";
	private static String systemDefaultDateTimeMSecFormat = "MM/dd/yyyy HH:mm:ss.SSS";
	private static String systemDefaultDateFormat = "MM/dd/yyyy";
	private static String systemDefaultTimeFormat = "HH:mm:ss";
	
	
	/**
	 * Get systems default format for date and time 
	 * 
	 * @return text "MM/dd/yyyy HH:mm:ss"
	 */
	public static String getSystemDefaultDateTimeFormat() {
		return systemDefaultDateTimeFormat;
	}
	public static String getSystemDefaultDateTimeMSecFormat() {
		return systemDefaultDateTimeMSecFormat;
	}

	/**
	 * Get systems default format for date 
	 * 
	 * @return text MM/dd/yyyy
	 */
	public static String getSystemDefaultDateFormat() {
		return systemDefaultDateFormat;
	}
	
	public static String getDatabaseDateTimeFormat() {
		return databaseDateTimeFormat;
	}
	public static String getDatabaseDateFormat() {
		return databaseDateTimeFormat;
	}
	
	

	/**
	 * Get systems default format for time 
	 * 
	 * @return text "HH:mm:ss"
	 */
	public static String getSystemDefaultTimeFormat() {
		return systemDefaultTimeFormat;
	}
	
	
	/**
	 * Returns the formatted date
	 * 
	 * @param date
	 *            to set dateobject
	 * 
	 * @param format
	 *            to set desired format
	 * @return returns date in desired format
	 */
	public static String formatDate(Date date, String format) {
		String dateTimeTxt = null;
		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			dateTimeTxt = sf.format(date);
		} catch (Exception ex) {
			logger.error("Invalid date string {} or invalid format {}", date, format);
		}
		return dateTimeTxt;
	}

	/**
	 * Returns the date in defaultTimeformat
	 * 
	 * @param date
	 *            to set dateObject
	 * @return date in defaultTimeformat
	 */
	public static String formatDate(Date date) {
		return formatDate(date, getSystemDefaultDateTimeFormat());
	}
	
	public static String getDateTimeTxtForDatabaseQuery(Date date) {
		return formatDate(date, getDatabaseDateTimeFormat());
	}

	public static String getDateTxtForDatabaseQuery(Date date) {
		return formatDate(date, getDatabaseDateTimeFormat());
	}

	public static String convertTxtToDateTxtForDatabaseQuery(String dataTimeTxt, String inputformat) {
		String dateTimeTxt = null;
		try {
			SimpleDateFormat inputFormatter = new SimpleDateFormat(inputformat);
			Date date = inputFormatter.parse(dataTimeTxt);
			dateTimeTxt = getDateTimeTxtForDatabaseQuery(date);
		} catch (Exception ex) {
			logger.error("Invalid date {} or invalid format {}", dataTimeTxt, inputformat);
			logger.error("Date conversion exception", ex);
		}
		return dateTimeTxt;
	}
	
	
	
	/**
	 * Returns the formatted date if Timestamp is the parameter
	 * 
	 * @param TimeStamp
	 *            to set TimeStampobject
	 * 
	 * @param format
	 *            to set desired format
	 * @return returns formatTs in desired format
	 */
	public static String formatDate(Timestamp timestamp, String format) {
		return formatDate(new Date(timestamp.getTime()), format);
	}

	/**
	 * Returns the date in defaultTimeformat if TimestampObject is the parameter
	 * 
	 * @param TimeStamp
	 *            to set TimestampObject
	 * @return formatString in defaultTimeformat
	 */
	public static String formatTime(Timestamp timestamp) {
		return formatDate(new Date(timestamp.getTime()));
	}
	
	public static String getDateTimeTxtForDatabaseQuery(Timestamp timestamp) {
		return formatDate(new Date(timestamp.getTime()), databaseDateTimeFormat);
	}

	public static String getDateTxtForDatabaseQuery(Timestamp timestamp) {
		return formatDate(new Date(timestamp.getTime()), databaseDateFormat);
	}
	
	/**
	 * Create a time date stamp as a text string 
	 * 
	 * @return  yyyyMMddHHmmss
	 */
	public static String createTimestampTxt() {
		StringBuilder timestampTxt = new StringBuilder();
		String DATE_FORMAT = "yyyyMMddHHmmss";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		Date date = new Date(System.currentTimeMillis());
		timestampTxt.append(formatter.format(date));
		return timestampTxt.toString();
	}
	
	
}
