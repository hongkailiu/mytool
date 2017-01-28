package tk.hongkailiu.mytool;

/**
 * Created by hongkailiu on 2017-01-21.
 */
public class MyTool {


  /* package */ static MyToolCommand myToolCommand = new MyToolCommand();

  public static void main(String[] args) {
    myToolCommand.parse(args);
    myToolCommand.execute();
  }
}
