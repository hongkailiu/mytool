package tk.hongkailiu.mytool;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import tk.hongkailiu.mytool.git.GitCommand;
import tk.hongkailiu.mytool.helper.AppCommand;

/**
 * Created by hongkailiu on 2017-01-21.
 */
@Slf4j
public class MyToolCommand implements Command {
  @Parameter(names = {"-h", "--help"}, help = true)
  private boolean help = false;

  private GitCommand git = new GitCommand();
  private AppCommand app = new AppCommand();
  /* package */ String subCommand;
  /* package */ Command command;

  // Only for test
  /* package */ boolean noArgsFlag = false;

  @Override
  public void execute() {
    if (command!=null) {
      command.execute();
    } else {
      log.warn("command is null");
    }
  }

  public void parse(String[] args) {
    JCommander jCommander = new JCommander(this);
    try {

      if (args==null || args.length==0) {
        noArgsFlag = true;
        args = new String[]{"-h"};
      }

      jCommander.setProgramName("mytool");
      jCommander.addCommand("git", git);
      jCommander.addCommand("app", app);
      jCommander.parse(args);
      if (help) {
        jCommander.usage();
        return;
      }
      subCommand = jCommander.getParsedCommand();

      if ("git".equals( subCommand)) {
        command = git;
      } else if ("app".equals( subCommand)) {
        command = app;
      }
    } catch (ParameterException e) {
      System.err.println(e.getMessage());
      //System.err.println("aaa" + jCommander.getParsedCommand());
      subCommand = jCommander.getParsedCommand();
      if (StringUtils.isBlank(subCommand)) {
        jCommander.usage();
      } else {
        jCommander.usage(subCommand);
      }
    }
  }

}
