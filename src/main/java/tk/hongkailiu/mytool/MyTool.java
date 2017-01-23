package tk.hongkailiu.mytool;

import org.kohsuke.args4j.CmdLineException;

/**
 * Created by hongkailiu on 2017-01-21.
 */
public class MyTool {

  /* package */ static MyToolCommand myToolCommand = new MyToolCommand();

  private static void doMain(String[] args) throws CmdLineException {
    myToolCommand.doMain(args);
    myToolCommand.execute();
  }

  public static void main(String[] args) {

    if (args == null) {
      throw new IllegalArgumentException("no args");
    }

    try {
      new MyTool().doMain(args);
    } catch (CmdLineException e) {
      myToolCommand.printUsage(System.err);
    }

  }
}
