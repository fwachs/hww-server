package com.twoclams.hww.server.cache.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import redis.clients.util.SafeEncoder;

public abstract class Bytes {

    /**
     * This helper method will serialize the given serializable object of type T
     * to a byte[], suitable for use as a value for a redis key, regardless of
     * the key type.
     * 
     * @param <T>
     * @param obj
     * @return
     */
    public static <T extends Serializable> byte[] encode(T o) {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(o);
            return bout.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error serializing object" + o + " => " + e);
        }
    }

    /**
     * The only reason to have this is to be able to compatible with java 1.5 :(
     * 
     * @param key
     * @return
     */
    public static byte[] encode(String key) {
        return SafeEncoder.encode(key);
    }
    
    public static byte[][] encode(String ... keys) {
        byte[][] bkeys = new byte[keys.length][];
        for (int i = 0; i < bkeys.length; ++i) {
            bkeys[i] = encode(keys[i]);
        }
        return bkeys;
    }

    /**
     * This helper method will assume that the byte[] provided are the
     * serialized bytes obtainable for an instance of type T obtained from
     * {@link ObjectOutputStream} and subsequently stored as a value for a redis
     * key (regardless of key type).
     * <p>
     * Specifically, this method will simply do:
     * 
     * <pre>
     * <code>
     * ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(bytes));
     * t = (T) oin.readObject();
     * </code>
     * </pre>
     * 
     * and returning the reference <i>t</i>, and throwing any exceptions
     * encountered along the way.
     * <p>
     * This method is the decoding peer of {@link Bytes#encode(Serializable)},
     * and it is assumed (and certainly recommended) that you use these two
     * methods in tandem.
     * <p>
     * Naturally, all caveats, rules, and considerations that generally apply to
     * {@link Serializable} and the Object Serialization specification apply.
     * 
     * @param <T>
     * @param bytes
     * @return the instance for <code><b>T</b></code>
     */
    @SuppressWarnings("unchecked")
    public static final <T extends Serializable> T decode(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        T t = null;
        Exception thrown = null;
        try {
            ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(bytes));
            t = (T) oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            thrown = e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            thrown = e;
        } catch (ClassCastException e) {
            e.printStackTrace();
            thrown = e;
        } finally {
            if (null != thrown)
                throw new RuntimeException("Error decoding byte[] data to instantiate java object - "
                        + "data at key may not have been of this type or even an object", thrown);
        }
        return t;
    }
    
}
