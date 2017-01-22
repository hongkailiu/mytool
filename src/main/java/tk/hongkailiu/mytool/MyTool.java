package tk.hongkailiu.mytool;

import lombok.extern.slf4j.Slf4j;
import tk.hongkailiu.mytool.git.GitTool;
import tk.hongkailiu.mytool.helper.AppInfoHelper;

/**
 * Created by hongkailiu on 2017-01-21.
 */
@Slf4j public class MyTool {

  public static void main(String[] args) {

    AppInfoHelper appInfoHelper = new AppInfoHelper();
    log.info("getAppVersion: " + appInfoHelper.getAppVersion());
    GitTool.main(args);
  }

}
