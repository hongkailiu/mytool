package tk.hongkailiu.mytool;

import lombok.extern.slf4j.Slf4j;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ParserProperties;
import org.kohsuke.args4j.spi.SubCommand;
import org.kohsuke.args4j.spi.SubCommandHandler;
import org.kohsuke.args4j.spi.SubCommands;
import tk.hongkailiu.mytool.git.GitCommand;

/**
 * Created by hongkailiu on 2017-01-21.
 */
@Slf4j
public class MyToolCommand implements Command {

  ///**/ static boolean enabled = true;

  @Argument(required = true, index = 0, metaVar = "command", usage = "subcommands, e.g., {git|TODO}", handler = SubCommandHandler.class)
  @SubCommands({
      @SubCommand(name = "git", impl = GitCommand.class),
  })
  /* */ Command command;

  public void doMain(String[] args) throws CmdLineException {

    ParserProperties parserProperties = ParserProperties.defaults();
    parserProperties.withUsageWidth(80);
    CmdLineParser parser = new CmdLineParser(this, parserProperties);

    parser.parseArgument(args);
  }

  @Override
  public void execute() {
    command.execute();
  }
}
