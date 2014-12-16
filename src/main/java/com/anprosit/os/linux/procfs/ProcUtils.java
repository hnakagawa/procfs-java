package com.anprosit.os.linux.procfs;

import java.math.BigInteger;
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
            private Map<ProcStat, BigInteger> cache = new HashMap<ProcStat, BigInteger>();

            @Override
            public int compare(ProcStat lh, ProcStat rh) {
                BigInteger lt = getTime(lh);
                BigInteger rt = getTime(rh);
                return rt.compareTo(lt);
            }

            private BigInteger getTime(ProcStat stat) {
                BigInteger t = cache.get(stat);
                if (t != null)
                    return t;

                BigInteger c = stat.utime.add(stat.stime);
                ProcStat last = t1.get(stat.pid);
                if (last != null)
                    c = c.subtract(last.utime.add(last.stime));

                cache.put(stat, c);

                return c;
            }
        });

        return newArray;
    }
}
