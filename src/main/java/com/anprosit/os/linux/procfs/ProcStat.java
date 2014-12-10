package com.anprosit.os.linux.procfs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hnakagawa on 14/11/12.
 */
public class ProcStat {
    public final int pid;

    public final String comm;

    public final State state;

    public final int ppid;

    public final int pgrp;

    public final int session;

    public final int ttyNr;

    public final int tpgid;

    public final long flags;

    public final long minflt;

    public final long cminflt;

    public final long majflt;

    public final long cmajflt;

    public final long utime;

    public final long stime;

    public final long cutime;

    public final long cstime;

    public final int priority;

    public final int nice;

    public final int numThreads;

    //TODO

    ProcStat(int pid, String comm, State state, int ppid, int pgrp, int session, int ttyNr, int tpgid, long flags, long minflt, long cminflt, long majflt, long cmajflt, long utime, long stime, long cutime, long cstime, int priority, int nice, int numThreads) {
        this.pid = pid;
        this.comm = comm;
        this.state = state;
        this.ppid = ppid;
        this.pgrp = pgrp;
        this.session = session;
        this.ttyNr = ttyNr;
        this.tpgid = tpgid;
        this.flags = flags;
        this.minflt = minflt;
        this.cminflt = cminflt;
        this.majflt = majflt;
        this.cmajflt = cmajflt;
        this.utime = utime;
        this.stime = stime;
        this.cutime = cutime;
        this.cstime = cstime;
        this.priority = priority;
        this.nice = nice;
        this.numThreads = numThreads;
    }

    @Override
    public String toString() {
        return String.format("pid=%d,comm=%s,state=%c,ppid=%d,pgrp=%d,session=%d,tty_nr=%d,tpgid=%d,flags=%d,minflt=%d,cminflt=%d,majflt=%d,cmajflt=%d,utime=%d,stime=%d,cutime=%d,cstime=%d,priority=%d,nice=%d,num_threads=%d\n",
                pid, comm, state.symbol, ppid, pgrp, session, ttyNr, tpgid, flags, minflt, cminflt, majflt, cmajflt, utime, stime, cutime, cstime, priority, nice, numThreads);
    }

    private static final Pattern pattern = Pattern.compile("(\\d+) \\(([^\\)]+)\\) ([RSDZTtW]) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+).*");

    static ProcStat newInstance(String data) {
        Matcher matcher = pattern.matcher(data);
        if (!matcher.find())
            throw new IllegalArgumentException(data);

        int pid = Integer.parseInt(matcher.group(1));
        String comm = matcher.group(2);
        State state = State.fromChar(matcher.group(3).charAt(0));
        int ppid = Integer.parseInt(matcher.group(4));
        int pgrp = Integer.parseInt(matcher.group(5));
        int session = Integer.parseInt(matcher.group(6));
        int ttyNr = Integer.parseInt(matcher.group(7));
        int tpgid = Integer.parseInt(matcher.group(8));
        long flags = Long.parseLong(matcher.group(9));
        long minflt = Long.parseLong(matcher.group(10));
        long cminflt = Long.parseLong(matcher.group(11));
        long majflt = Long.parseLong(matcher.group(12));
        long cmajflt = Long.parseLong(matcher.group(13));
        long utime = Long.parseLong(matcher.group(14));
        long stime = Long.parseLong(matcher.group(15));
        long cutime = Long.parseLong(matcher.group(16));
        long cstime = Long.parseLong(matcher.group(17));
        int priority = Integer.parseInt(matcher.group(18));
        int nice = Integer.parseInt(matcher.group(19));
        int numThreads = Integer.parseInt(matcher.group(20));

        return new ProcStat(pid, comm, state, ppid, pgrp, session, ttyNr, tpgid, flags, minflt, cminflt, majflt,
                cmajflt, utime, stime, cutime, cstime, priority, nice, numThreads);
    }

    public static enum State {
        RUNNING('R'),
        SLEEPING('S'),
        DISK_SLEEPING('D'),
        ZOMBIE('Z'),
        STOPPED('T'),
        TRACING_STOP('t'),
        PAGING('W');

        public char symbol;

        State(char symbol) {
            this.symbol = symbol;
        }

        public static State fromChar(char c) {
            State[] states = values();
            for (State state : states) {
                if (state.symbol == c)
                    return state;
            }

            throw new IllegalArgumentException();
        }
    }
}
