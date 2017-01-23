package tk.hongkailiu.mytool;

import java.io.OutputStream;
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
  MyToolCommand myToolCommandMock;

  @Before
  public void setUp() {
    MyTool.myToolCommand = myToolCommandMock;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMainWithNull() throws Exception {
    MyTool.main(null);
  }

  @Test
  public void testMainWithUnknownArgs() throws Exception {
    String[] args = new String[]{"unknown-args"};
    Mockito.doNothing().when(myToolCommandMock).doMain(args);
    Mockito.doNothing().when(myToolCommandMock).execute();
    MyTool.main(args);
  }

  @Test
  public void testMainWithException() throws Exception {
    String[] args = new String[]{"unknown-args"};
    Mockito.doThrow(
        new CmdLineException((CmdLineParser) null, new IllegalArgumentException("error-msg")))
        .when(myToolCommandMock).doMain(args);
    Mockito.doNothing().when(myToolCommandMock).printUsage(Mockito.any(OutputStream.class));
    MyTool.main(args);
    Mockito.verify(myToolCommandMock, Mockito.times(1)).printUsage(Mockito.any(OutputStream.class));
  }
}