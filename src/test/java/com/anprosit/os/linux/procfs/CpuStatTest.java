package com.anprosit.os.linux.procfs;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hnakagawa on 14/11/13.
 */
public class CpuStatTest {

    @Test
    public void newInstance() {
        CpuStat stat = CpuStat.newInstance("cpu  1000 1001 1002 1003 1004 1 1980 0 0 0\n"+
                "cpu0 1005 1006 1007 1008 1009 1 349 0 0 0\n" +
                "cpu2 2005 2006 2007 2008 2009 1 349 0 0 0\n" +
                "intr 183954467 53 0 0 0 0 0 0 0 8 162029993 0 0 0 0 0 0 0 0 133499 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 91645 83897 197 11 399232 1503 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "ctxt 345390834\n" +
                "btime 1111\n" +
                "processes 2222\n" +
                "procs_running 3333\n" +
                "procs_blocked 4444\n" +
                "softirq 20838239 2 10477148 9 78374 88041 0 152212 7054597 77002 2910854\n");

        assertEquals(1000, stat.cpu.user);
        assertEquals(1001, stat.cpu.nice);
        assertEquals(1002, stat.cpu.system);
        assertEquals(1003, stat.cpu.idle);
        assertEquals(1004, stat.cpu.iowait);
        assertEquals(2, stat.cpus.size());

        CpuStat.Cpu cpu = stat.cpus.get(0);
        assertEquals(1005, cpu.user);
        assertEquals(1006, cpu.nice);
        assertEquals(1007, cpu.system);
        assertEquals(1008, cpu.idle);
        assertEquals(1009, cpu.iowait);

        cpu = stat.cpus.get(1);
        assertEquals(2005, cpu.user);
        assertEquals(2006, cpu.nice);
        assertEquals(2007, cpu.system);
        assertEquals(2008, cpu.idle);
        assertEquals(2009, cpu.iowait);

        assertEquals(1111, stat.btime);
        assertEquals(2222, stat.processes);
        assertEquals(3333, stat.procsRunning);
        assertEquals(4444, stat.procsBlocked);
    }

    @Test
    public void toString_() {
        CpuStat stat = CpuStat.newInstance("cpu  1000 1001 1002 1003 1004 1 1980 0 0 0\n"+
                "cpu0 1005 1006 1007 1008 1009 1 349 0 0 0\n" +
                "btime 1111\n" +
                "processes 2222\n" +
                "procs_running 3333\n" +
                "procs_blocked 4444\n");
        assertEquals("cpu user=1000,nice=1001,system=1002,idle=1003,iowait=1004\n" +
                "cpu user=1005,nice=1006,system=1007,idle=1008,iowait=1009\n" +
                "btime=1111,processes=2222,procs_running=3333,procs_blocked=4444\n", stat.toString());
    }
}
