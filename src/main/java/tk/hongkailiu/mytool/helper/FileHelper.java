package tk.hongkailiu.mytool.helper;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * Created by hongkailiu on 2017-01-29.
 */
public class FileHelper {

  public List<String> listFiles(File dir, List<String> extensions, boolean recursive) {
    if (!dir.isDirectory()) {
      return Collections.emptyList();
    }
    Collection<File> files =
        FileUtils.listFiles(dir, extensions.toArray(new String[extensions.size()]),
            recursive);
    return files.stream().map(file -> file.getAbsolutePath()).collect(Collectors.toList());
  }

  public String getContentFromResource(String path) throws IOException {
    ClassLoader classloader = getClass().getClassLoader();
    InputStream is = classloader.getResourceAsStream(path);
    try {
      return IOUtils.toString(is, Charset.defaultCharset());
    } finally {
      IOUtils.closeQuietly(is);
    }
  }
}
