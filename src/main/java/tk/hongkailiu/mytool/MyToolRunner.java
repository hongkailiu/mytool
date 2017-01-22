package tk.hongkailiu.mytool;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.OptionHandlerFilter;
import org.kohsuke.args4j.ParserProperties;
import tk.hongkailiu.mytool.git.GitTool;

/**
 * Created by hongkailiu on 2017-01-21.
 */
@Slf4j
public class MyToolRunner {

  /* package */ static final String UNKNOWN_ARGS = "unknown args";
  /* package */ static final String EMPTY_ARGUMENT_LIST = "empty argument list";

  @Option(name = "-git", usage = "git module")
  private boolean git;

  @Argument
  private List<String> arguments = new ArrayList();

  /* package */ GitTool gitTool = new GitTool();


  public void doMain(String[] args) throws CmdLineException {

    ParserProperties parserProperties = ParserProperties.defaults();
    parserProperties.withUsageWidth(80);
    CmdLineParser parser = new CmdLineParser(this, parserProperties);

    parser.parseArgument(args);

    if (arguments.isEmpty()) {
      throw new CmdLineException(parser, new IllegalArgumentException(EMPTY_ARGUMENT_LIST));
    }

    if (git) {
      gitTool.doMain(args);
    } else {
      throw new CmdLineException(parser, new IllegalArgumentException(UNKNOWN_ARGS + ": " + args[0]));
    }
  }

}
