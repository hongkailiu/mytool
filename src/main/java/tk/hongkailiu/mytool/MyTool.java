package tk.hongkailiu.mytool;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.OptionHandlerFilter;

/**
 * Created by hongkailiu on 2017-01-21.
 */
public class MyTool {

  /* package */ static MyToolRunner myToolRunner = new MyToolRunner();

  private static void doMain(String[] args) throws CmdLineException {
    myToolRunner.doMain(args);
  }

  public static void main(String[] args) {

    // -git -findOrphan
    //AppInfoHelper appInfoHelper = new AppInfoHelper();
    //System.out.println("version: " + appInfoHelper.getAppVersion());

    if (args==null) {
      throw new IllegalArgumentException("no args");
    }

    try {
      new MyTool().doMain(args);
    } catch (CmdLineException e) {
      System.err.println("error: " + e.getMessage());
    }

  }
}
