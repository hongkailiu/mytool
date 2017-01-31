package tk.hongkailiu.mytool.helper;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by hongkailiu on 2017-01-30.
 */
public class AppCommandTest {

  private AppCommand unitUnderTest;

  @Before
  public void setUp() throws Exception {
    unitUnderTest = new AppCommand();
  }

  @Test
  public void testExecute() throws Exception {
    unitUnderTest.execute();
  }

  @Test
  public void testExecute2() throws Exception {
    unitUnderTest.version = true;
    unitUnderTest.execute();
  }

}