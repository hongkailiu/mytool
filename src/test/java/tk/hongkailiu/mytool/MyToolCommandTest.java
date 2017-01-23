package tk.hongkailiu.mytool;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.assertj.core.api.Assertions;
import org.junit.After;
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
public class MyToolCommandTest {

  private MyToolCommand unitUnderTest;
  @Mock
  Command commandMock;
  @Mock
  CmdLineParser cmdLineParserMock;

  @Before
  public void setUp() {
    unitUnderTest = new MyToolCommand();
    unitUnderTest.command = commandMock;
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test(expected = NullPointerException.class)
  public void testDoMainNull() throws Exception {
    unitUnderTest.doMain(null);
  }

  @Test
  public void testDoMainUnknowArgs() throws Exception {
    try {
      unitUnderTest.doMain(new String[]{"unknown-args"});
      Assertions.fail("CmdLineException is expected");
    } catch (CmdLineException e) {
      Assertions.assertThat(e.getMessage()).contains("is not a valid value");
    }
  }

  @Test
  public void testDoMainEmptyArgs() throws Exception {
    try {
      unitUnderTest.doMain(new String[]{});
      Assertions.fail("CmdLineException is expected");
    } catch (CmdLineException e) {
      Assertions.assertThat(e.getMessage()).contains("Argument").contains("is required");
    }
  }

  @Test
  public void testDoMainGitWithArg1() throws Exception {
    String[] args = new String[]{"git", "-folder", "aaa", "-extensions", "a b c"};
    unitUnderTest.doMain(args);
  }

  @Test
  public void testDoMainGitWithArg2() throws Exception {
    try {
      String[] args = new String[]{"git", "-xxxfolder", "aaa", "-extensions", "a b c"};
      unitUnderTest.doMain(args);
    } catch (CmdLineException e) {
      Assertions.assertThat(e.getMessage()).contains("-xxxfolder")
          .contains("is not a valid option");
    }
  }

  @Test
  public void testDoMainGitWithArg3() throws Exception {
    try {
      String[] args = new String[]{"git", "-folder", "aaa", "-xxxextensions", "a b c"};
      unitUnderTest.doMain(args);
    } catch (CmdLineException e) {
      Assertions.assertThat(e.getMessage()).contains("-xxxextensions")
          .contains("is not a valid option");
    }
  }

  @Test
  public void testDoMainGitWithArg4() throws Exception {
    try {
      String[] args = new String[]{"git", "-folder", "aaa"};
      unitUnderTest.doMain(args);
    } catch (CmdLineException e) {
      Assertions.assertThat(e.getMessage()).contains("is required");
    }
  }

  @Test
  public void testDoMainGitWithArg5() throws Exception {
    try {
      String[] args = new String[]{"git", "-folder", "aaa"};
      unitUnderTest.doMain(args);
    } catch (CmdLineException e) {
      Assertions.assertThat(e.getMessage()).contains("is required");
    }
  }

  @Test
  public void testExecute() throws Exception {
    Mockito.doNothing().when(commandMock).execute();
    unitUnderTest.execute();
    Mockito.verify(commandMock, Mockito.times(1)).execute();
  }

  @Test
  public void testPrintUsage() throws Exception {
    unitUnderTest.parser = cmdLineParserMock;
    OutputStream os = new ByteArrayOutputStream();
    Mockito.doNothing().when(cmdLineParserMock).printUsage(os);
    unitUnderTest.printUsage(os);
    Mockito.verify(cmdLineParserMock, Mockito.times(1)).printUsage(os);
  }
}