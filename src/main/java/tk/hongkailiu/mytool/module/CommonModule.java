package tk.hongkailiu.mytool.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import tk.hongkailiu.mytool.helper.AppInfoHelper;
import tk.hongkailiu.mytool.helper.FileHelper;

/**
 * Created by hongkailiu on 2017-01-21.
 */
public class CommonModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(AppInfoHelper.class).in(Singleton.class);
    bind(FileHelper.class).in(Singleton.class);
  }
}
