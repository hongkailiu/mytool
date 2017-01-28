package tk.hongkailiu.mytool.git;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.FileConverter;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import tk.hongkailiu.mytool.Command;
import tk.hongkailiu.mytool.git.OrphanFinder.OrphanFinderFactory;
import tk.hongkailiu.mytool.module.CommonModule;
import tk.hongkailiu.mytool.module.GitModule;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@Slf4j
@Parameters(commandDescription = "Git command")
public class GitCommand implements Command {

  @Parameter(names = "-extensions", description = "The extensions")
  private List<String> extensions = new ArrayList();

  @Parameter(names = "-folder", description = "The folder name", converter = FileConverter.class)
  private File folder;

  @Parameter(names = "-action", description = "Action", required = true)
  private String action;

  @Inject
  private OrphanFinderFactory orphanFinderFactory;

  @Override
  public void execute() {
    Injector injector =
        Guice.createInjector(new CommonModule(), new GitModule());
    GitHelper gitHelper = new GitHelper(injector);
    log.info("GitCommand ...");

    if (StringUtils.isBlank(action)) {
      throw new ParameterException("empty action");
    }


    System.out.println("GitCommand ...");
    System.out.println("folder: " + folder.getAbsolutePath());
    System.out.println("extensions: " + Arrays.toString(extensions.toArray()));
    List<File> files = gitHelper.findOrphans(folder, extensions);
    System.out.println("orphan number: " + files.size());
    files.forEach(f -> System.out.println("orphan file: " + f.getAbsolutePath()));
  }
}
