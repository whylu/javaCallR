/*
 * Copyright© Institute for Information Industry
 * All rights reserved.
 * 本程式碼係屬財團法人資訊工業策進會版權所有，在未取得本會書面同意前，不得複製、散佈或修改。
 */
package org.whylu.rest.ctrl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whylu.util.RScriptAppUtils;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：Test.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author minglungweng
 *@version 1.0
 *@since 1.0
 */

@Controller
@RequestMapping("/callR")
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    
    //  http://localhost:8080/whylufun/callR
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String test(HttpServletRequest req, HttpServletResponse rsp) {
        return "yoooooooooooo";        
    }
    
    //  http://localhost:8080/whylufun/callR/run/{scriptName}
    //  ex: http://localhost:8080/whylufun/callR/run/testScript1
    @RequestMapping(method=RequestMethod.GET, value="/run/{scriptName}")
    public @ResponseBody String runR(HttpServletRequest req, HttpServletResponse rsp,
            @PathVariable String scriptName, 
            @RequestParam(value="arg1", required=false, defaultValue="1") String arg1,
            @RequestParam(value="arg2", required=false, defaultValue="2") String arg2,
            @RequestParam(value="arg3", required=false, defaultValue="1") String arg3
            ) throws IOException, JSONException, InterruptedException {
        

        logger.debug("scriptName="+scriptName);
        String scriptLocation = RScriptAppUtils.getValue(scriptName);
        logger.debug("scriptLocation="+scriptLocation);
        logger.debug("arg1="+arg1);
        logger.debug("arg2="+arg2);
        logger.debug("arg2="+arg3);
        
        // prepare data for R-------------------------

        JSONObject json = new JSONObject();
        json.put("arg1", arg1);
        json.put("arg2", arg2);
        json.put("arg3", arg3);
        
        json.put("a", new JSONArray("[1,2,3,4,5,6,7,8,9,10]"));
        json.put("b", new JSONArray("[7,7,7]"));
        logger.debug("json="+json.toString());  
        
        RConnection c = null;
        String result = null;
        try {
        // should we start -------------------------
            c = new RConnection();
            c.eval("source(\""+scriptLocation+"\")");  // need call source() to import R file
        
        
        
            String data = json.toString();
            c.assign("mydata",  data );  //assign data 
    
            REXP valueReturned = c.eval("scriptStart("+ "mydata" +")");  //call r function in R file
    
            result = valueReturned.asString();
            logger.debug(valueReturned.asString());  
        
        } catch (RserveException e) { 
            c.close();
            e.printStackTrace();
        } catch (REXPMismatchException e) {
            c.close();
            e.printStackTrace();
        }  finally {
            c.close();
        }
        
        
       
        if(result==null) {
            result="you get nothing";
        }
        logger.debug("done!");  
        return result;
    }

    //  http://localhost:8080/whylufun/callR/get/{scriptName}
    //  ex: http://localhost:8080/whylufun/callR/get/testScript1
    @RequestMapping(method=RequestMethod.GET, value="/get/{scriptName}")
    public @ResponseBody String getR(HttpServletRequest req, HttpServletResponse rsp,
            @PathVariable String scriptName ) throws IOException {

        logger.debug("scriptName="+scriptName);
        String scriptLocation = RScriptAppUtils.getValue(scriptName);
        
        String text = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(scriptLocation));

            StringBuilder builder = new StringBuilder("<div style='white-space: pre-wrap;'>");
            String aux = "";
            while ((aux = reader.readLine()) != null) {
                builder.append(aux+"\n");
            }

            
            builder.append("</div>");
            text = builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            
            reader.close();
        }
        

        
        return text;
    }
    

}
