package tk.hongkailiu.mytool.git;

import java.io.File;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import tk.hongkailiu.mytool.helper.FileHelper;

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
    unitUnderTest = new OrphanFinder(packFolder, false);
    unitUnderTest.fileHelper = new FileHelper();
  }

  @After
  public void tearDown() throws Exception {
    unitUnderTest = null;
  }

  @Test
  public void testFind1() throws Exception {
    File file1 = new File(packFolder, "1." + "idx");
    Assertions.assertThat(file1.createNewFile()).isTrue();
    List<File> orphans = unitUnderTest.find();
    Assertions.assertThat(orphans).isNotEmpty().hasSize(1);
  }

  @Test
  public void testFind2() throws Exception {
    File file1 = new File(packFolder, "1." + "idx");
    File file2 = new File(packFolder, "1." + "bitmap");
    File file3 = new File(packFolder, "1." + "pack");
    Assertions.assertThat(file1.createNewFile()).isTrue();
    Assertions.assertThat(file2.createNewFile()).isTrue();
    Assertions.assertThat(file3.createNewFile()).isTrue();
    List<File> orphans = unitUnderTest.find();
    Assertions.assertThat(orphans).isEmpty();
  }

  @Test
  public void testFindPackFolderIsFile() throws Exception {
    File file1 = new File(packFolder, "1." + "idx");
    unitUnderTest = new OrphanFinder(file1, false);
    unitUnderTest.fileHelper = new FileHelper();
    Assertions.assertThat(file1.createNewFile()).isTrue();
    List<File> orphans = unitUnderTest.find();
    Assertions.assertThat(orphans).isEmpty();
  }

  @Test
  public void testFindPackFileIsFolder() throws Exception {
    File file1 = new File(packFolder, "1." + "bitmap");
    Assertions.assertThat(file1.mkdir()).isTrue();
    List<File> orphans = unitUnderTest.find();
    Assertions.assertThat(orphans).isEmpty();
  }

  @Test
  public void testFindPackFileWithUnknownExtension() throws Exception {
    File file1 = new File(packFolder, "1." + "aaa");
    Assertions.assertThat(file1.createNewFile()).isTrue();
    List<File> orphans = unitUnderTest.find();
    Assertions.assertThat(orphans).isEmpty();
  }

  @Test(expected = NullPointerException.class)
  public void testConstructor1() throws Exception {
    unitUnderTest = new OrphanFinder(null, false);
  }

}