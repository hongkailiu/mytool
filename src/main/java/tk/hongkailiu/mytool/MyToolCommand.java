package tk.hongkailiu.mytool;

import lombok.extern.slf4j.Slf4j;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ParserProperties;
import org.kohsuke.args4j.spi.SubCommand;
import org.kohsuke.args4j.spi.SubCommandHandler;
import org.kohsuke.args4j.spi.SubCommands;

import java.io.OutputStream;

import tk.hongkailiu.mytool.git.GitCommand;
import tk.hongkailiu.mytool.helper.AppCommand;

/**
 * Created by hongkailiu on 2017-01-21.
 */
@Slf4j
public class MyToolCommand implements Command {

  private CmdLineParser parser =
      new CmdLineParser(this, ParserProperties.defaults().withUsageWidth(80));

  @Argument(required = true, index = 0, metaVar = "command", usage = "subcommands", handler = SubCommandHandler.class)
  @SubCommands({
      @SubCommand(name = "git", impl = GitCommand.class),
      @SubCommand(name = "app", impl = AppCommand.class),
  })
  /* pacakge */ Command command;

  public void doMain(String[] args) throws CmdLineException {
    parser.parseArgument(args);
  }

  @Override
  public void execute() {
    command.execute();
  }

  /* package */ void printUsage(OutputStream os) {
    parser.printUsage(os);
  }
}
