package cn.org.caicp.common.cache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

public class CaictCacheEventListener implements CacheEventListener {

    public static final CaictCacheEventListener instance = new CaictCacheEventListener();

    @Override
    public void notifyElementRemoved(Ehcache cache, Element element)
            throws CacheException {
        System.err.println("notifyElementRemoved");
    }

    @Override
    public void notifyElementPut(Ehcache cache, Element element)
            throws CacheException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[").append(cache.getName()).append("] [size:")
                .append(cache.getSize()).append("] put(")
                .append(element.getObjectKey()).append(",")
                .append(element.getObjectValue()).append(")");
        System.err.println(buffer.toString());
    }

    @Override
    public void notifyElementUpdated(Ehcache cache, Element element)
            throws CacheException {
        System.err.println("notifyElementUpdated");
    }

    @Override
    public void notifyElementExpired(Ehcache cache, Element element) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("expired[").append(cache.getName()).append("] [size:")
                .append(cache.getSize()).append("] ")
                .append(element.getObjectKey());
        System.err.println(buffer.toString());
    }

    @Override
    public void notifyElementEvicted(Ehcache cache, Element element) {
        System.err.println("notifyElementEvicted");
    }

    @Override
    public void notifyRemoveAll(Ehcache cache) {
        System.err.println("notifyRemoveAll");
    }

    @Override
    public void dispose() {
        // System.err.println("dispose");
    }

    public Object clone() throws CloneNotSupportedException {
        return instance;
    }

}
