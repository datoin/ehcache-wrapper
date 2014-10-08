package org.datoin.cache;

import junit.framework.Assert;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.junit.Test;

public class EhcacheWrapperTest {

    private String cacheName = "test";
    private static CacheManager manager;

    @org.junit.Before
    public void setUp() throws Exception {
        manager = CacheManager.create();

    }

    @org.junit.After
    public void tearDown() throws Exception {
        manager.shutdown();
    }

    @Test
    public void testPut() throws Exception {
        EhcacheWrapper<String,Object> ecw = new EhcacheWrapper<String, Object>(cacheName, manager);
        String keyPut = "keyPut1";
        String valPut = "valPut1";
        ecw.put(keyPut, valPut);
        Assert.assertTrue("Same value should be retrieved ", valPut.equals(ecw.get(keyPut)));
    }

    @Test
    public void testGet() throws Exception {
        EhcacheWrapper<String,Object> ecw = new EhcacheWrapper<String, Object>(cacheName, manager);
        String keyPut = "keyGet1";
        String valPut = "valGet1";
        ecw.put(keyPut, valPut);
        Assert.assertTrue("Same value should be retrieved ", valPut.equals(ecw.get(keyPut)));

    }

    @Test
    public void testRemove() throws Exception {
        EhcacheWrapper<String,Object> ecw = new EhcacheWrapper<String, Object>(cacheName, manager);
        String keyRemove = "keyRemove1";
        String valRemove = "valRemove1";
        ecw.put(keyRemove, valRemove);
        int size = ecw.getSize();
        Assert.assertTrue("Same value should be retrieved ", valRemove.equals(ecw.get(keyRemove)));
        Assert.assertTrue("Key removal should return true", ecw.remove(keyRemove));
        Assert.assertFalse("Non existing Key removal should return false", ecw.remove(keyRemove));
        Assert.assertEquals("size should reduce by 1", size-1, ecw.getSize());

    }

    @Test
    public void testGetSize() throws Exception {
        EhcacheWrapper<String,Object> ecw = new EhcacheWrapper<String, Object>(cacheName, manager);
        String keySize = "keyRemove1";
        String valSize = "valRemove1";
        int size = ecw.getSize();
        ecw.put(keySize, valSize);
        Assert.assertEquals("size should increase by 1", size+1, ecw.getSize());
    }

    @Test
    public void testGetCache() throws Exception {
        EhcacheWrapper<String,Object> ecw = new EhcacheWrapper<String, Object>(cacheName, manager);
        Ehcache wraperCache = ecw.getCache();
        Assert.assertEquals("Wrapper and manager cache names should be same", wraperCache.getName(), cacheName);
        Assert.assertEquals("Wrapper and manager cache managers should be same", wraperCache.getCacheManager(), manager);
    }
}