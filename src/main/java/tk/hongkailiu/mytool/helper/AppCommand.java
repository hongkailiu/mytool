package tk.hongkailiu.mytool.helper;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.kohsuke.args4j.Option;

import lombok.extern.slf4j.Slf4j;
import tk.hongkailiu.mytool.Command;
import tk.hongkailiu.mytool.module.CommonModule;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@Slf4j
public class AppCommand implements Command {

  @Option(name="--version",usage="show version")
  private boolean version;

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
