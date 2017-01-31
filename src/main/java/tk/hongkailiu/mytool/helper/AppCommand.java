package tk.hongkailiu.mytool.helper;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.extern.slf4j.Slf4j;
import tk.hongkailiu.mytool.Command;
import tk.hongkailiu.mytool.module.CommonModule;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@Slf4j
@Parameters(commandDescription = "App command")
public class AppCommand implements Command {

  @Parameter(names = { "-v", "--version" }, description = "show version")
  /* package */ boolean version;

  private AppInfoHelper appInfoHelper;

  @Override
  public void execute() {
    Injector injector = Guice.createInjector(new CommonModule());
    appInfoHelper = injector.getInstance(AppInfoHelper.class);
    log.info("AppCommand ...");
    if (version) {
      System.out.println(appInfoHelper.getAppVersion());
    }
  }
}
