package se.webinventions.util

import java.util.concurrent.ConcurrentHashMap

/**
 * Created by IntelliJ IDEA.
 * User: kushal
 * Date: 10/25/11
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
class BeanBag {

    private static ConcurrentHashMap<String, Object> beanHolder = new ConcurrentHashMap<String, Object>()

    public static void put(String key, Object value) {
        beanHolder.put(key, value)
    }

    public static Object fetch(String key) {
        return beanHolder.get(key)
    }

    public static boolean check(String key) {
        return beanHolder.containsKey(key)
    }

}
