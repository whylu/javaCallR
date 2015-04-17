/*
 * Copyright© Institute for Information Industry
 * All rights reserved.
 * 本程式碼係屬財團法人資訊工業策進會版權所有，在未取得本會書面同意前，不得複製、散佈或修改。
 */
package org.whylu.util;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：CalendarUtils.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author minglungweng
 *@version 1.0
 *@since 1.0
 */
@Service("rScriptAppUtils")
public class RScriptAppUtils implements ApplicationContextAware{
    private static final Logger logger = LoggerFactory.getLogger(RScriptAppUtils.class);
    
    private static ApplicationContext appContext;
    private static ResourceBundle resource = ResourceBundle.getBundle("rScriptDef",Locale.TAIWAN);
    
    @Autowired
    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        RScriptAppUtils.appContext = appContext;
    }
    

            

    /**
     * get value from googleCalendarConfig.properties
     * @param key
     * @return
     */
    public static String getValue(String key) {
        if (resource != null) {
            logger.debug("get property key=" + key);
            return resource.getString(key);
        }
        return null;
    }

    public static File getP12File() throws IOException {
        return appContext.getResource(resource.getString("service.account.private.key.p12.file")).getFile();
    }
    
}
