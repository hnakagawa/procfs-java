package com.anprosit.os.linux.procfs;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hnakagawa on 14/11/11.
 */
final class FileUtils {

    private FileUtils() {}

    public static List<File> getFiles(String path) throws IOException {
        return getFiles(path, null);
    }

    public static List<File> getFiles(String path, FilenameFilter filter) throws IOException {
        File dir = new File(path);
        if (!dir.isDirectory())
            throw new IOException(path + " is not directory");

        String[] paths = dir.list(filter);
        if (paths == null)
            return new ArrayList<File>(0);

        List<File> files = new ArrayList<File>(paths.length);
        for (String p : paths)
            files.add(new File(p));

        return files;
    }

    public static String read(String path) throws IOException {
        return read(new File(path));
    }

    public static String read(File file) throws IOException {
        FileReader reader = null;

        try {
            reader = new FileReader(file);
            char[] buf = new char[128];
            StringBuilder builder = new StringBuilder();
            int size;
            while ((size = reader.read(buf, 0, buf.length)) > 0)
                builder.append(buf, 0, size);

            return builder.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
