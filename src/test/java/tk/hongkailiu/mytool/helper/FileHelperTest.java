package tk.hongkailiu.mytool.helper;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import tk.hongkailiu.mytool.git.OrphanFinder;

/**
 * Created by hongkailiu on 2017-01-29.
 */
public class FileHelperTest {

  private FileHelper unitUnderTest;
  @Before
  public void setUp() throws Exception {
    unitUnderTest = new FileHelper();
  }

  @Test
  public void listFiles() throws Exception {
    // TODO
    /*//String path = "/Users/hongkailiu/Documents/programming/project/";
    String path = "/tmp/123";
    List<String> paths = unitUnderTest.listFiles(new File(path),
        OrphanFinder.EXTENSIONS, true);
    paths.stream().forEach(s -> System.out.println("p: " + s));*/
  }

}