package tk.hongkailiu.mytool.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class AppInfoHelper {

  private String appVersion;

  private static final String EMPTY_VERSION_FILE = "empty version file";
  private static final String NO_VERSION_FILE_FOUND = "no version file found";
  private static final String VERSION = "version";

  public String getAppVersion() {
    if (appVersion == null) {
      synchronized (this) {
        if (appVersion == null) {
          appVersion = loadVersionFromResource();
        }
      }
    }
    return appVersion;
  }

  private String loadVersionFromResource() {
    return loadVersionFromResource(VERSION);
  }

  private String loadVersionFromResource(String path) {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(path);
    try {
      String version = StringUtils.trim(IOUtils.toString(is));
      if (StringUtils.isNotBlank(version)) {
        return version;
      }
      log.error(EMPTY_VERSION_FILE);
      return EMPTY_VERSION_FILE;
    } catch (IOException | NullPointerException e) {
      log.error(e.getMessage(), e);
      return NO_VERSION_FILE_FOUND;
    } finally {
      IOUtils.closeQuietly(is);
    }
  }
}