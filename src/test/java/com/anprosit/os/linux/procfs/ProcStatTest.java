package com.anprosit.os.linux.procfs;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by hnakagawa on 14/11/13.
 */
public class ProcStatTest {

    @Test
    public void newInstance() throws Exception {
        ProcStat state = ProcStat.newInstance("11 (aaa) S 22 33 44 55 -66 77 88 99 1010 2020 3030 4040 5050 6060 7070 8080 9090 0 642115 725131264 17070 18446744073709551615 1 1 0 0 0 0 0 4098 1073807360 18446744073709551615 0 0 17 1 0 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(11, state.pid);
        assertEquals("aaa", state.comm);
        assertEquals('S', state.state.symbol);
        assertEquals(22, state.ppid);
        assertEquals(33, state.pgrp);
        assertEquals(44, state.session);
        assertEquals(55, state.ttyNr);
        assertEquals(-66, state.tpgid);
        assertEquals(77, state.flags);
        assertEquals(88, state.minflt);
        assertEquals(99, state.cminflt);
        assertEquals(1010, state.majflt);
        assertEquals(2020, state.cmajflt);
        assertEquals(new BigInteger("3030"), state.utime);
        assertEquals(new BigInteger("4040"), state.stime);
        assertEquals(new BigInteger("5050"), state.cutime);
        assertEquals(new BigInteger("6060"), state.cstime);
        assertEquals(7070, state.priority);
        assertEquals(8080, state.nice);
        assertEquals(9090, state.numThreads);
    }

    @Test
    public void newInstanceWithEmptyName() {
        ProcStat state = ProcStat.newInstance("11 () S 22 33 44 55 -66 77 88 99 1010 2020 3030 4040 5050 6060 7070 8080 9090 0 642115 725131264 17070 18446744073709551615 1 1 0 0 0 0 0 4098 1073807360 18446744073709551615 0 0 17 1 0 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals("pid=11,comm=,state=S,ppid=22,pgrp=33,session=44,tty_nr=55,tpgid=-66,flags=77,minflt=88,cminflt=99,majflt=1010,cmajflt=2020,utime=3030,stime=4040,cutime=5050,cstime=6060,priority=7070,nice=8080,num_threads=9090\n", state.toString());
    }

    @Test
    public void toString_() {
        ProcStat state = ProcStat.newInstance("11 (aaa) S 22 33 44 55 -66 77 88 99 1010 2020 3030 4040 5050 6060 7070 8080 9090 0 642115 725131264 17070 18446744073709551615 1 1 0 0 0 0 0 4098 1073807360 18446744073709551615 0 0 17 1 0 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals("pid=11,comm=aaa,state=S,ppid=22,pgrp=33,session=44,tty_nr=55,tpgid=-66,flags=77,minflt=88,cminflt=99,majflt=1010,cmajflt=2020,utime=3030,stime=4040,cutime=5050,cstime=6060,priority=7070,nice=8080,num_threads=9090\n", state.toString());
    }
}
