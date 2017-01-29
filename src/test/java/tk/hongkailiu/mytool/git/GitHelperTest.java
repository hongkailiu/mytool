package tk.hongkailiu.mytool.git;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tk.hongkailiu.mytool.git.OrphanFinder.OrphanFinderFactory;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@RunWith(MockitoJUnitRunner.class)
public class GitHelperTest {

  private GitHelper unitUnderTest;
  @Mock
  OrphanFinderFactory orphanFinderFactoryMock;
  @Mock
  OrphanFinder orphanFinderMock;
  List<File> files = Arrays.asList(new File("1.txt"));

  @Before
  public void setUp() throws Exception {
    Injector injector = Guice.createInjector(new TestModule());
    unitUnderTest = new GitHelper(injector);
    Mockito.when(orphanFinderFactoryMock.create(null, false)).thenReturn(orphanFinderMock);
    Mockito.when(orphanFinderMock.find()).thenReturn(files);
  }

  @Test
  public void testFindOrphans() throws Exception {
    Assertions.assertThat(unitUnderTest.findOrphans(null, false)).isSameAs(files);
  }

  class TestModule extends AbstractModule {

    @Override
    protected void configure() {
      bind(OrphanFinderFactory.class).toInstance(orphanFinderFactoryMock);
    }
  }


}