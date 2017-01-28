package tk.hongkailiu.mytool;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by hongkailiu on 2017-01-22.
 */
@RunWith(MockitoJUnitRunner.class)
public class MyToolTest {

  @Mock
  private MyToolCommand myToolCommandMock;

  @Before
  public void setUp() {
    MyTool.myToolCommand = myToolCommandMock;
  }

  @Test
  public void testMain() throws Exception {
    String[] args = new String[]{"some-arg"};
    Mockito.doNothing().when(myToolCommandMock).parse(args);
    Mockito.doNothing().when(myToolCommandMock).execute();
    MyTool.main(args);
    Mockito.verify(myToolCommandMock, Mockito.times(1)).parse(args);
    Mockito.verify(myToolCommandMock, Mockito.times(1)).execute();
  }

  @Test
  public void testConstructor() throws Exception {
    Assertions.assertThat(new MyTool()).isNotNull();
  }
}