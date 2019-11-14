package com.jeffosmond.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @function   : 读取xml文件工具类，XML->InputStream
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/27 9:57
 */
public class ResourcesUtils {

    /**
     * 根据文件路径获取文件InputStream
     * @param resource
     * @return
     */
    public static InputStream getResourcesAsStream(String resource){
        return ResourcesUtils.class.getClassLoader().getResourceAsStream(resource);
    }

    /**
     * 根据文件路径获取文件Reader
     * @param resource
     * @return
     */
    public static Reader getResourcesAsReader(String resource){
        return new InputStreamReader(getResourcesAsStream(resource));
    }
}
