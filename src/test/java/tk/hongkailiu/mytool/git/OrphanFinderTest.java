package tk.hongkailiu.mytool.git;

import java.io.File;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Created by hongkailiu on 2017-01-21.
 */
public class OrphanFinderTest {

  private OrphanFinder unitUnderTest;

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();
  private File packFolder;

  @Before
  public void setUp() throws Exception {
    packFolder = folder.newFolder("pack");
    String[] extensions = new String[]{"idx", "bitmap", "pack"};
    unitUnderTest = new OrphanFinder(packFolder, extensions);
  }

  @After
  public void tearDown() throws Exception {
    unitUnderTest = null;
  }

  @Test
  public void testFind() throws Exception {
    File file1 = new File(packFolder, "1." + "idx");
    Assertions.assertThat(file1.createNewFile()).isTrue();
    List<File> orphans = unitUnderTest.find();
    Assertions.assertThat(orphans).isNotEmpty();
  }

}