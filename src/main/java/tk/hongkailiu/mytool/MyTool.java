package tk.hongkailiu.mytool;

import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.OptionHandlerFilter;
import org.kohsuke.args4j.ParserProperties;
import tk.hongkailiu.mytool.helper.AppInfoHelper;
import tk.hongkailiu.mytool.helper.ToolHelper;

/**
 * Created by hongkailiu on 2017-01-21.
 */
public class MyTool {

  @Option(name = "-git", usage = "git module")
  private boolean git;

  @Argument
  private List<String> arguments = new ArrayList<String>();

  /* package */ static ToolHelper toolHelper = new ToolHelper();

  public static void main(String[] args) {

    // -git -findOrphan
    AppInfoHelper appInfoHelper = new AppInfoHelper();
    System.out.println("version: " + appInfoHelper.getAppVersion());
    new MyTool().doMain(args);
  }

  private void doMain(String[] args) {
    ParserProperties parserProperties = ParserProperties.defaults();
    parserProperties.withUsageWidth(80);
    CmdLineParser parser = new CmdLineParser(this, parserProperties);

    try {
      parser.parseArgument(args);

      if (arguments.isEmpty()) {
        throw new CmdLineException(parser, new IllegalArgumentException("empty argument list"));
      }

    } catch (CmdLineException e) {
      System.err.println(e.getMessage());
      System.err.println(this.getClass().getSimpleName() + " [options...] arguments...");
      parser.printUsage(System.err);
      System.err.println();

      System.err.println("  Example: " + this.getClass().getSimpleName() + " " + parser
          .printExample(OptionHandlerFilter.ALL));

      return;
    }

    if (git) {
      toolHelper.getGitTool().doMain(args);
    }
  }

}
