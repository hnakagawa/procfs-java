package com.anprosit.os.linux.procfs;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hnakagawa on 14/11/13.
 */
public class FdInfoTest {
    @Test
    public void newInstance() {
        Fdinfo info = Fdinfo.newInstance(1,
                "pos:    0\n" +
                "flags:  0100002\n" +
                "mnt_id: 19\n");

        assertEquals(0, info.pos);
        assertEquals(100002, info.flags);
        assertEquals(19, info.mntId);
    }

    @Test
    public void toString_() {
        Fdinfo info = Fdinfo.newInstance(1,
                "pos:    0\n" +
                "flags:  0100002\n" +
                "mnt_id: 19\n");
        assertEquals("fd=1,pos=0,flags=100002,mnt_id=19\n", info.toString());
    }
}
