package tk.hongkailiu.mytool;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.args4j.CmdLineException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tk.hongkailiu.mytool.git.GitTool;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@RunWith(MockitoJUnitRunner.class)
public class MyToolRunnerTest {

  private MyToolRunner unitUnderTest;
  @Mock
  GitTool gitToolMock;

  @Before
  public void setUp() {
    unitUnderTest = new MyToolRunner();
    unitUnderTest.gitTool = gitToolMock;
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
      Assertions.assertThat(e.getMessage()).contains(MyToolRunner.UNKNOWN_ARGS);
    }
  }

  @Test
  public void testDoMainEmptyArgs() throws Exception {
    try {
      unitUnderTest.doMain(new String[]{});
      Assertions.fail("CmdLineException is expected");
    } catch (CmdLineException e) {
      Assertions.assertThat(e.getMessage()).contains(MyToolRunner.EMPTY_ARGUMENT_LIST);
    }
  }

  @Test
  public void testDoMainGitWithArg() throws Exception {
    String[] args = new String[]{"-git", "0"};
    Mockito.doNothing().when(gitToolMock).doMain(args);
    unitUnderTest.doMain(args);
  }

}