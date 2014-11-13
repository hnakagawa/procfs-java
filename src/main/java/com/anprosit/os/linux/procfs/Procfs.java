package com.anprosit.os.linux.procfs;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by hnakagawa on 14/11/11.
 */
public class Procfs {
    private static final FilenameFilter processFileNameFilter = new FilenameFilter() {
        private Pattern pattern = Pattern.compile("^[0-9]+$");

        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    };

    private static volatile Procfs instance;

    private final String base;

    Procfs(String base) {
        this.base = base;
    }

    public List<Proc> getProcs() throws IOException {
        List<File> files = FileUtils.getFiles(base, processFileNameFilter);
        List<Proc> procs = new ArrayList<Proc>(files.size());
        for (File file : files)
            procs.add(new Proc(base + file.getName() + "/", Integer.parseInt(file.getName())));
        return procs;
    }

    public CpuStat getStat() throws IOException {
        String data = FileUtils.read(base + "stat");
        return CpuStat.newInstance(data);
    }

    public static Procfs getInstance() {
        if (instance == null) {
            synchronized (Procfs.class) {
                if (instance == null)
                    instance = new Procfs("/proc/");
            }
        }

        return instance;
    }
}
