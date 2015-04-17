/*
 * Copyright© Institute for Information Industry
 * All rights reserved.
 * 本程式碼係屬財團法人資訊工業策進會版權所有，在未取得本會書面同意前，不得複製、散佈或修改。
 */
package org.whylu.rest.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：template.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author minglungweng
 *@version 1.0
 *@since 1.0
 */

@Controller
@RequestMapping("/hbase")
public class template {
    
    //http://localhost:8080/whyluWebApp/hbase/
    //http://localhost:8080/whyluWebApp/hbase/cdh-master/2181/DevicePointData?rk=iBMS_0001&rk=iBMS_0058&v=3
    //http://localhost:8080/whylufun/hbase/cdh-master/2181/DevicePointData?rk=iBMS_0001&rk=iBMS_0058&v=3

//    @RequestMapping(method=RequestMethod.GET, value="/{quorum}/{port}/{tableName}")
//    public void test(HttpServletRequest req, HttpServletResponse rsp,
//            @PathVariable("quorum") String quorum, 
//            @PathVariable("port") String port, 
//            @PathVariable("tableName") String tableName, 
//            @RequestParam("rk") List<String> rowkeys, 
//            @RequestParam(value="v", required=false, defaultValue="1") Integer version, 
//            @RequestParam(value="mycallback", required=false) String mycallback
//            ) throws IOException {
//
//        System.out.println(quorum);
//        System.out.println(port);
//        System.out.println(tableName);
//        System.out.println(rowkeys);
//        System.out.println(version);
//        System.out.println(mycallback);
//        
//        HbaseTableUtil tableutil = new HbaseTableUtil()
//                                    .setConnectionConfig(quorum, port, null)
//                                    .setTableName(tableName);
//        List<HRow> list = tableutil.getRows(rowkeys, version);
//        System.out.println(list);
//
//        JSONObject json = new JSONObject();
//        json.put("result", list.toString());
////        json.put("result", "123456");
//
//        rsp.addHeader("Access-Control-Allow-Origin", "*");
//        PrintWriter writer = rsp.getWriter();
////        String.format("%s(%s)", mycallback, )
////        writer.write();
////        writer.flush();
////        writer.close();
//        
//        try {
//            CommunicationUtils.JSON2Client(writer, json);
//        } finally {
//            IOUtils.closeQuietly(writer);
//        }
//    }
}
