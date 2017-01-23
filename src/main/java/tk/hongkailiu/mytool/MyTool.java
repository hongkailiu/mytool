package tk.hongkailiu.mytool;

import org.kohsuke.args4j.CmdLineException;

/**
 * Created by hongkailiu on 2017-01-21.
 */
public class MyTool {

  /* package */ static MyToolCommand myToolCommand = new MyToolCommand();

  private static void doMain(String[] args) throws CmdLineException {
    //myToolRunner.doMain(args);
    myToolCommand.doMain(args);
    myToolCommand.execute();
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
