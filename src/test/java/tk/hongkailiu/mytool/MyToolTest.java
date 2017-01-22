package tk.hongkailiu.mytool;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@RunWith(MockitoJUnitRunner.class)
public class MyToolTest {

  @Mock
  MyToolRunner myToolRunnerMock;

  @Before
  public void setUp() {
    MyTool.myToolRunner = myToolRunnerMock;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMainWithNull() throws Exception {
    MyTool.main(null);
  }

  @Test
  public void testMainWithUnknownArgs() throws Exception {
    String[] args = new String[]{"unknown-args"};
    Mockito.doNothing().when(myToolRunnerMock).doMain(args);
    MyTool.main(args);
  }

  @Test
  public void testMainWithException() throws Exception {
    String[] args = new String[]{"unknown-args"};
    Mockito.doThrow(
        new CmdLineException((CmdLineParser) null, new IllegalArgumentException("error-msg")))
        .when(myToolRunnerMock).doMain(args);
    MyTool.main(args);
  }
}