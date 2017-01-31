package tk.hongkailiu.mytool.git;

import com.beust.jcommander.ParameterException;
import java.io.File;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Created by hongkailiu on 2017-01-30.
 */
public class GitCommandTest {

  private GitCommand unitUnderTest;

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @Before
  public void setUp() throws Exception {
    unitUnderTest = new GitCommand();
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void testExecute() throws Exception {
    try {
      unitUnderTest.execute();
      Assertions.fail("ParameterException is expected");
    } catch (ParameterException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("empty action");
    }
  }

  @Test
  public void testExecute2() throws Exception {
    try {
      unitUnderTest.action = "some-action";
      unitUnderTest.execute();
      Assertions.fail("ParameterException is expected");
    } catch (ParameterException e) {
      Assertions.assertThat(e.getMessage()).contains("some-action")
          .contains("not supported git action");
    }
  }

  @Test
  public void testExecute3() throws Exception {
    try {
      unitUnderTest.action = GitCommand.FIND_ORPHANS;
      unitUnderTest.execute();
      Assertions.fail("ParameterException is expected");
    } catch (ParameterException e) {
      Assertions.assertThat(e.getMessage()).contains("no such a folder")
          .contains("null");
    }
  }

  @Test
  public void testExecute4() throws Exception {
    try {
      unitUnderTest.action = GitCommand.FIND_ORPHANS;
      File packDir = folder.newFolder("some-folder");
      Assertions.assertThat(packDir.delete()).isTrue();
      unitUnderTest.folder = packDir;
      unitUnderTest.execute();
      Assertions.fail("ParameterException is expected");
    } catch (ParameterException e) {
      Assertions.assertThat(e.getMessage()).contains("no such a folder")
          .contains("some-folder");
    }
  }

  @Test
  public void testExecute5() throws Exception {
    unitUnderTest.action = GitCommand.FIND_ORPHANS;
    File packDir = folder.newFolder("some-folder");
    unitUnderTest.folder = packDir;
    unitUnderTest.execute();
  }
}