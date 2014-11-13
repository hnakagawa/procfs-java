package com.anprosit.os.linux.procfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hnakagawa on 14/11/12.
 */
public class CpuStat {
    public final Cpu cpu;

    public final List<Cpu> cpus;

    public final long btime;

    public final int processes;

    public final int procsRunning;

    public final int procsBlocked;

    CpuStat(Cpu cpu, List<Cpu> cpus, long btime, int processes, int procsRunning, int procsBlocked) {
        this.cpu = cpu;
        this.cpus = Collections.unmodifiableList(cpus);
        this.btime = btime;
        this.processes = processes;
        this.procsRunning = procsRunning;
        this.procsBlocked = procsBlocked;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(cpu.toString());
        for (Cpu cpu : cpus)
            builder.append(cpu.toString());

        builder.append(String.format("btime=%d,processes=%d,procs_running=%d,procs_blocked=%d\n", btime, processes, procsRunning, procsBlocked));
        return builder.toString();
    }

    private static final Pattern pattern = Pattern.compile("([a-zA-Z]+) (\\d+).*");

    static CpuStat newInstance(String data) {
        String[] lines = data.split("\n");

        Cpu cpu = Cpu.newInstance(lines[0]);
        List<Cpu> cpus = new ArrayList<Cpu>();
        long btime = 0;
        int processes, procRunning, procsBlocked;

        processes = procRunning = procsBlocked = -1;

        for (int i = 1; i < lines.length; i++) {
            if (lines[i].startsWith("cpu"))
                cpus.add(Cpu.newInstance(lines[i]));
            else if (lines[i].startsWith("btime"))
                btime = getLongValue(lines[i]);
            else if (lines[i].startsWith("processes"))
                processes = getIntValue(lines[i]);
            else if (lines[i].startsWith("procs_running"))
                procRunning = getIntValue(lines[i]);
            else if (lines[i].startsWith("procs_blocked"))
                procsBlocked = getIntValue(lines[i]);
        }

        return new CpuStat(cpu, cpus, btime, processes, procRunning, procsBlocked);
    }

    private static long getLongValue(String line) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find())
            throw new IllegalArgumentException(line);

        return Long.parseLong(matcher.group(2));
    }

    private static int getIntValue(String line) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find())
            throw new IllegalArgumentException(line);

        return Integer.parseInt(matcher.group(2));
    }

    public static class Cpu {
        public final long user;

        public final long nice;

        public final long system;

        public final long idle;

        public final long iowait;

        //TODO

        Cpu(long user, long nice, long system, long idle, long iowait) {
            this.user = user;
            this.nice = nice;
            this.system = system;
            this.idle = idle;
            this.iowait = iowait;
        }

        @Override
        public String toString() {
            return String.format("cpu user=%d,nice=%d,system=%d,idle=%d,iowait=%d\n", user, nice, system, idle, iowait);
        }

        private static final Pattern pattern = Pattern.compile("cpu\\d* +(\\d+) (\\d+) (\\d+) (\\d+) (\\d+) (\\d+) (\\d+).*");

        static Cpu newInstance(String data) {
            Matcher matcher = pattern.matcher(data);
            if (!matcher.find())
                throw new IllegalArgumentException(data);

            long user = Long.parseLong(matcher.group(1));
            long nice = Long.parseLong(matcher.group(2));
            long system = Long.parseLong(matcher.group(3));
            long idle = Long.parseLong(matcher.group(4));
            long iowait = Long.parseLong(matcher.group(5));

            return new Cpu(user, nice, system, idle, iowait);
        }
    }
}