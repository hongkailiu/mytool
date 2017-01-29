package tk.hongkailiu.mytool;

import com.beust.jcommander.ParameterException;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tk.hongkailiu.mytool.git.GitCommand;
import tk.hongkailiu.mytool.helper.AppCommand;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@RunWith(MockitoJUnitRunner.class)
public class MyToolCommandTest {

  private MyToolCommand unitUnderTest;
  @Mock
  Command commandMock;

  @Before
  public void setUp() {
    unitUnderTest = new MyToolCommand();
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void testParseNull() throws Exception {
    Assertions.assertThat(unitUnderTest.noArgsFlag).isFalse();
    unitUnderTest.parse(null);
    Assertions.assertThat(unitUnderTest.noArgsFlag).isTrue();
  }

  @Test
  public void testParseEmptyArgs() throws Exception {
    Assertions.assertThat(unitUnderTest.noArgsFlag).isFalse();
    unitUnderTest.parse(new String[]{});
    Assertions.assertThat(unitUnderTest.noArgsFlag).isTrue();
  }

  @Test
  public void testParseUnknowArgs() throws Exception {
    unitUnderTest.parse(new String[]{"unknown-args"});
    Assertions.assertThat(unitUnderTest.subCommand).isNull();
  }



  @Test
  public void testParseGitWithArg1() throws Exception {
    String[] args = new String[]{"git", "-action", GitCommand.FIND_ORPHANS, "-folder", "aaa"};
    unitUnderTest.parse(args);
    Assertions.assertThat(unitUnderTest.command).isInstanceOf(GitCommand.class);
    GitCommand gitCommand = (GitCommand) unitUnderTest.command;
    Assertions.assertThat(gitCommand.getAction()).isEqualTo(GitCommand.FIND_ORPHANS);
    Assertions.assertThat(gitCommand.getFolder().getName()).isEqualTo("aaa");
  }

  @Test
  public void testParseGitWithArg2() throws Exception {
    String[] args = new String[]{"git"};
    unitUnderTest.parse(args);
    Assertions.assertThat(unitUnderTest.subCommand).isEqualTo("git");
    Assertions.assertThat(unitUnderTest.command).isNull();
  }

  @Test
  public void testParseAppWithArg3() throws Exception {
    String[] args = new String[]{"app"};
    unitUnderTest.parse(args);
    Assertions.assertThat(unitUnderTest.subCommand).isEqualTo("app");
    // no required args defined yet
    Assertions.assertThat(unitUnderTest.command).isInstanceOf(AppCommand.class);
  }

  @Test
  public void testExecute() throws Exception {
    unitUnderTest.command = commandMock;
    Mockito.doNothing().when(commandMock).execute();
    unitUnderTest.execute();
    Mockito.verify(commandMock, Mockito.times(1)).execute();
  }

  @Test
  public void testExecute2() throws Exception {
    unitUnderTest.execute();
  }

  @Test
  public void testExecuteWithException() throws Exception {
    unitUnderTest.command = commandMock;
    Mockito.doThrow(new ParameterException("error-msg")).when(commandMock).execute();
    unitUnderTest.execute();
    Mockito.verify(commandMock, Mockito.times(1)).execute();
  }

  @Test
  public void testGetCommand() throws Exception {
    Assertions.assertThat(unitUnderTest.getCommand("?")).isNull();
  }
}