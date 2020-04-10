package com.eprworld.nrcl.controller;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eprworld.nrcl.util.FormatUtil;
import com.eprworld.nrcl.util.OsUtility;
import com.eprworld.nrcl.util.VersionInfo;

@Controller
public class HomeController {

	@Value("${server.port}")
	private String serverPortNbr;	

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
    @RequestMapping("/")
    String index(Model model){
    	logger.info("GET home page");

    	model.addAttribute("version", VersionInfo.appVersionNbr);
    	
    	Calendar cal = Calendar.getInstance();
    	Date now = cal.getTime();
    	model.addAttribute("toDate", FormatUtil.formatDate(now, FormatUtil.getSystemDefaultDateTimeFormat()));
    	cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 00, 00, 00);
    	model.addAttribute("fromDate", FormatUtil.formatDate(cal.getTime(), FormatUtil.getSystemDefaultDateTimeFormat()));
    	
    	model.addAttribute("imgRootDir", OsUtility.isWindowsOs() ? "C:/RemoteMonitor/images" : "/raid/images" );
    	
        return "index";
    }
    
    public String getAppHttpServerPortNbr() {
    	return serverPortNbr;
    }
    
	
}
