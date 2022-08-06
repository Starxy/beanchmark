/*
 * Copyright 2014 Ben Manes. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cc.starxy.cache;

import cc.starxy.cache.impl.CaffeineCache;
import cc.starxy.cache.impl.ConcurrentMapCache;
import cc.starxy.cache.impl.GuavaCache;
import cc.starxy.cache.impl.HuTimedCache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * A factory for creating a {@link BasicCache} implementation.
 *
 * @author ben.manes@gmail.com (Ben Manes)
 */
public enum CacheType {

    /* --------------- Unbounded --------------- */

    ConcurrentHashMap {
        @Override
        public <K, V> BasicCache<K, V> create(int maximumSize) {
            return new ConcurrentMapCache<>(new ConcurrentHashMap<>(maximumSize));
        }
    },

    /* --------------- Bounded --------------- */

    Caffeine {
        @Override
        public <K, V> BasicCache<K, V> create(int maximumSize) {
            return new CaffeineCache<>(maximumSize);
        }
    },
    Guava {
        @Override
        public <K, V> BasicCache<K, V> create(int maximumSize) {
            return new GuavaCache<>(maximumSize);
        }
    },
    HuTimedCache {
        @Override
        public <K, V> BasicCache<K, V> create(int maximumSize) {
            return new HuTimedCache<>(maximumSize);
        }
    };

    /**
     * The number of hash table segments.
     */
    public static final int CONCURRENCY_LEVEL = 64;

    /**
     * Creates the cache with the maximum size. Note that some implementations may evict prior to
     * this threshold, and it is the caller's responsibility to adjust accordingly.
     */
    public abstract <K, V> BasicCache<K, V> create(int maximumSize);
}