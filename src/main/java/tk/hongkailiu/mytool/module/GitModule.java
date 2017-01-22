package tk.hongkailiu.mytool.module;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import tk.hongkailiu.mytool.git.OrphanFinder.OrphanFinderFactory;

/**
 * Created by hongkailiu on 2017-01-21.
 */
public class GitModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new FactoryModuleBuilder()
        .build(OrphanFinderFactory.class));
  }
}
