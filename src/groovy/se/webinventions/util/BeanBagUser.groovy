package se.webinventions.util

/**
 * Created by IntelliJ IDEA.
 * User: kushal
 * Date: 10/25/11
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BeanBagUser {

    public def storeInBeanBag = {String key, Object value ->
        BeanBag.put(key, value)
    }

    public def readFromBeanBag = {String key ->
        return BeanBag.fetch(key)
    }

    public def beanBagContains = {key ->
        return BeanBag.check(key)
    }

}