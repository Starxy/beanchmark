package cc.starxy.cache.impl;

import cc.starxy.cache.BasicCache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author DONG Jixing
 */
public final class HuTimedCache<K, V> implements BasicCache<K, V> {
    private final TimedCache<K, V> cache;
    /**
     * 默认缓存时长 单位s
     */
    private static final Long DEFAULT_TIMEOUT = 5 * 60 * 1000L;
    /**
     * 默认清理间隔时间 单位s
     */
    private static final Long CLEAN_TIMEOUT = 5 * 60 * 1000L;

    public HuTimedCache(int maximumSize) {
        cache = CacheUtil.newTimedCache(DEFAULT_TIMEOUT);
    }

    @Override
    public @Nullable V get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
