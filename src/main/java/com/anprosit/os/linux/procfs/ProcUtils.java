package com.anprosit.os.linux.procfs;

import java.util.*;

/**
 * Created by hnakagawa on 14/11/17.
 */
public final class ProcUtils {
    private ProcUtils() {}

    public static List<ProcStat> sortByCpuUsage(List<ProcStat> t1, List<ProcStat> t2) {
        Map<Integer, ProcStat> map = new HashMap<Integer, ProcStat>();
        for (ProcStat stat : t1)
            map.put(stat.pid, stat);
        return sortByCpuUsage(map, t2);
    }

    public static List<ProcStat> sortByCpuUsage(final Map<Integer, ProcStat> t1, List<ProcStat> t2) {
        List<ProcStat> newArray = new ArrayList<ProcStat>(t2);
        Collections.sort(newArray, new Comparator<ProcStat>() {
            private Map<Integer, Long> cache = new HashMap<Integer, Long>();

            @Override
            public int compare(ProcStat lh, ProcStat rh) {
                long lt = getTime(lh);
                long rt = getTime(rh);
                return lt >= rt ? -1 : 1;
            }

            private long getTime(ProcStat stat) {
                Long t = cache.get(stat.pid);
                if (t != null)
                    return t;

                long c = stat.utime + stat.stime;
                ProcStat last = t1.get(stat.pid);
                if (last != null)
                    c = c - (last.utime + last.stime);

                cache.put(stat.pid, c);

                return c;
            }
        });

        return newArray;
    }
}
