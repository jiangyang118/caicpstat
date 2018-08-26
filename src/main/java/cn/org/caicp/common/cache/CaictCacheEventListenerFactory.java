package cn.org.caicp.common.cache;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

public class CaictCacheEventListenerFactory extends CacheEventListenerFactory {

    @Override
    public CacheEventListener createCacheEventListener(Properties properties) {
        return CaictCacheEventListener.instance;
    }

}
