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

import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TokenMapTest {
    
    TokenMap<String, Object> map = TokenMap.of();
    
    
    @ParameterizedTest
    @MethodSource({"map_provider"})
    void containsKey(TokenMap<String, Object> map) {
        map.put("a", int.class, 1);
        assertTrue(map.containsKey("a", int.class));
    }
    
    
    @Test
    void containsValue() {
        map.put("a", int.class, 1);
        assertTrue(map.containsValue(1));
    }
    
    
    @ParameterizedTest
    @MethodSource({"map_provider"})
    void get(TokenMap<String, Object> map) {
        map.put("a", int.class, 1);
        assertEquals(1, (int) map.get("a", int.class));
    }
    
    
    @ParameterizedTest
    @MethodSource({"map_provider"})
    void getOrDefault_value(TokenMap<String, Object> map) {
        map.put("a", int.class, 1);
        assertEquals(1, (int) map.getOrDefault("a", int.class, 2));
    }
    
    
    @ParameterizedTest
    @MethodSource({"map_provider"})
    void getOrDefault_default(TokenMap<String, Object> map) {
        map.map().put(TokenMap.key("a", String.class), "b");
        assertEquals(1, (int) map.getOrDefault("a", int.class, 1));
    }
    
    
    @ParameterizedTest
    @MethodSource({"map_provider"})
    void put(TokenMap<String, Object> map) {
        map.put("a", String.class, "b");
        assertEquals("b", map.put("a", String.class, "c"));
    }
    
    
    static Stream<Arguments> map_provider() {
        TokenMap<String, Object> hashed = TokenMap.of(1);
        TokenMap<String, Object> proxied = TokenMap.of(new HashMap<>());
        return Stream.of(of(hashed), of(proxied));
    }
    
}


@ExtendWith(MockitoExtension.class)
class KeyTest {
    
    Key<String, String> key = TokenMap.key("name", String.class);
    
    
    @Test
    void equals_same() {
        assertTrue(key.equals(key));
    }
    
    
    @Test
    void equals_hash() {
        assertFalse(key.equals(TokenMap.key("name", int.class)));
    }
    
    
    @Test
    void toString_value() {
        assertEquals("Key[name: name class: java.lang.String]", key.toString());
    }
    
}
