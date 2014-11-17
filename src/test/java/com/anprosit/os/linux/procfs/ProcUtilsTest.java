package com.anprosit.os.linux.procfs;

import org.junit.Test;

import java.util.List;

/**
 * Created by hnakagawa on 14/11/17.
 */
public class ProcUtilsTest {
    @Test
    public void sortByCpuUsage() throws Exception {
        Procfs procfs = Procfs.getInstance();
        List<ProcStat> stats = procfs.getProcStats();
        ProcUtils.sortByCpuUsage(stats, procfs.getProcStats());
    }
}
