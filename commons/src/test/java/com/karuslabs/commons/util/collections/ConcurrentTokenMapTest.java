/*
 * The MIT License
 *
 * Copyright 2018 Karus Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.karuslabs.commons.util.collections;

import com.karuslabs.commons.util.collections.TokenMap.Key;

import java.util.concurrent.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ConcurrentTokenMapTest {
    
    ConcurrentTokenMap<String, Object> map = ConcurrentTokenMap.of();
    
    
    @Test
    void containsKey() {
        map.put("a", String.class, "b");
        assertTrue(map.containsKey("a", String.class));
    }
    
    
    @Test
    void get() {
        map.put("a", int.class, 1);
        assertEquals(1, (int) map.get("a", int.class));
    }
    
    
    @Test
    void getOrDefault() {
        map.map().put(TokenMap.key("a", int.class), map);
        assertEquals(1, (int) map.getOrDefault("a", int.class, 1));
    }
    
}


@ExtendWith(MockitoExtension.class)
class ConcurrentHashTokenMapTest {
    
    ConcurrentTokenMap<String, Object> map = ConcurrentTokenMap.of(1);
    
    
    @Test
    void map() {
        assertSame(map, map.map());
    }
    
}


@ExtendWith(MockitoExtension.class)
class ConcurrentProxiedTokenMapTest {
    
    ConcurrentMap<Key<String, ? extends Object>, Object> proxied = new ConcurrentHashMap<>();
    ConcurrentTokenMap<String, Object> map = ConcurrentTokenMap.of(proxied);
    
    
    @Test
    void map() {
        assertSame(proxied, map.map());
    }
    
}
