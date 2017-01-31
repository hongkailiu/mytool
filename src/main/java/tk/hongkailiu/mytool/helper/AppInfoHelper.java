package tk.hongkailiu.mytool.helper;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class AppInfoHelper {

  private String appVersion;

  private static final String EMPTY_VERSION_FILE = "empty version file";
  private static final String NO_VERSION_FILE_FOUND = "no version file found";
  /* package */ static final String VERSION = "version";

  @Inject
  /* package */ FileHelper fileHelper;

  public String getAppVersion() {
    if (appVersion == null) {
      synchronized (this) {
        if (appVersion == null) {
          appVersion = loadVersionFromResource(VERSION);
        }
      }
    }
    return appVersion;
  }

  private String loadVersionFromResource(String path) {
    try {
      String version = StringUtils.trim(fileHelper.getContentFromResource(path));
      if (StringUtils.isNotBlank(version)) {
        return version;
      }
      log.error(EMPTY_VERSION_FILE);
      return EMPTY_VERSION_FILE;
    } catch (IOException | NullPointerException e) {
      log.error(e.getMessage(), e);
      return NO_VERSION_FILE_FOUND;
    }
  }
}