package com.htzhny.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htzhny.util.HttpUtil;

import com.alibaba.fastjson.JSONObject;

/**
 * ΢�Ų�����ֱ�ӱ������Ϣ���ں�̨��ʼ����ȡ
 */
@Controller
@RequestMapping(value="/init")
public class InitController {
	Logger log = Logger.getLogger(InitController.class);
	HttpUtil http = HttpUtil.getInstance();
	
    //��¼
    @SuppressWarnings("finally")
	@RequestMapping(value="getWxInfo", method = RequestMethod.POST)
    public @ResponseBody JSONObject getWxInfo(@RequestBody Map<String,Object> params,HttpServletRequest request) {
    	JSONObject jsonObject = new JSONObject();
    	String code = (String) params.get("code");
    	String appId="wxb22aba87cf707a20";
    	String secret="daf3cb960bff96ff3432486983a6ce0e";   //������������ƽ̨С����
//    	String appId="wx6fff2ac4e4144e7b";
//    	String secret="018e0375b1ff44baf4ead4e752cf0b36";//���ܣ�С����
    	String result="1";
    	try {
	    	String address = "https://api.weixin.qq.com/sns/jscode2session";
	    	 //�������
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("appid", appId);//���Ǹýӿ���Ҫ�Ĳ���
	        param.put("secret", secret);//���Ǹýӿ���Ҫ�Ĳ���
	        param.put("js_code", code);//���Ǹýӿ���Ҫ�Ĳ���
	        
	        param.put("grant_type", "authorization_code");
	        String res = http.get(address, param, null);
	        System.out.println(res);//��ӡ���ز���
//	        log.info("΢������openid ���ؽ��="+res);
	        res = res.substring(res.indexOf("{"));//��ȡ
	        jsonObject.put("message",res);//��ӡ
	        
	        
	        jsonObject.put("appId", appId);
			jsonObject.put("secret", secret);
			jsonObject.put("result", result);
//			return jsonObject;
            
	       
    	} catch (Exception e) {
            // TODO �쳣
            e.printStackTrace();
        }finally{
        	return jsonObject;
        }
		
    }
}
