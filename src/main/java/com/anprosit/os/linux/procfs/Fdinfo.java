package com.anprosit.os.linux.procfs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hnakagawa on 14/11/11.
 */
public class Fdinfo {
    public final int fd;

    public final int pos;

    public final int flags;

    public final int mntId;

    Fdinfo(int fd, int pos, int flags, int mntId) {
        this.fd = fd;
        this.pos = pos;
        this.flags = flags;
        this.mntId = mntId;
    }

    @Override
    public String toString() {
        return String.format("fd=%d,pos=%d,flags=%d,mnt_id=%d\n", fd, pos, flags, mntId);
    }

    private static final Pattern pattern = Pattern.compile("pos:[ \t]*(\\d+)\nflags:[ \t]*(\\d+)\nmnt_id+:[ \t]*(\\d+)\n");

    static Fdinfo newInstance(int fd, String data) {
        Matcher matcher = pattern.matcher(data);
        if (!matcher.find())
            throw new IllegalArgumentException(data);

        int pos = Integer.parseInt(matcher.group(1));
        int flags = Integer.parseInt(matcher.group(2));
        int mntId = Integer.parseInt(matcher.group(3));

        return new Fdinfo(fd, pos, flags, mntId);
    }
}
