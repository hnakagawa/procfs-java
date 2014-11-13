package com.anprosit.os.linux.procfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hnakagawa on 14/11/11.
 */
public class Proc {
    private final String prefix;

    public final int pid;

    Proc(String prefix, int pid) {
        this.prefix = prefix;
        this.pid = pid;
    }

    public String getCmdline() throws IOException {
        return FileUtils.read(prefix + "cmdline");
    }

    public File getCwd() {
        return new File(prefix + "cwd");
    }

    public ProcStat getStat() throws IOException {
        return ProcStat.newInstance(FileUtils.read(prefix + "stat"));
    }

    public List<Fdinfo> getFdinfo() throws IOException {
        String path = prefix + "fdinfo/";
        List<File> files = FileUtils.getFiles(path, null);
        List<Fdinfo> fdinfos = new ArrayList<Fdinfo>(files.size());
        for (File file : files) {
            try {
                fdinfos.add(Fdinfo.newInstance(Integer.parseInt(file.getName()), FileUtils.read(path + file.getName())));
            } catch (FileNotFoundException exp) {
                //ignore
            }
        }

        return fdinfos;
    }

    @Override
    public String toString() {
        return String.format("pid=%d\n", pid);
    }
}
