package tk.hongkailiu.mytool.helper;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by hongkailiu on 2017-01-30.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppInfoHelperTest {

  private AppInfoHelper unitUnderTest;
  @Mock
  private FileHelper fileHelperMock;

  @Before
  public void setUp() throws Exception {
    unitUnderTest = new AppInfoHelper();
    unitUnderTest.fileHelper = fileHelperMock;
  }

  @Test
  public void testGetAppVersion() throws Exception {
    Mockito.when(fileHelperMock.getContentFromResource(AppInfoHelper.VERSION))
        .thenReturn("some-version");
    unitUnderTest.getAppVersion();
    unitUnderTest.getAppVersion();
    Mockito.verify(fileHelperMock, Mockito.times(1)).getContentFromResource(AppInfoHelper.VERSION);
  }

  @Test
  public void testGetAppVersion2() throws Exception {
    Mockito.when(fileHelperMock.getContentFromResource(AppInfoHelper.VERSION))
        .thenReturn("");
    unitUnderTest.getAppVersion();
    Mockito.verify(fileHelperMock, Mockito.times(1)).getContentFromResource(AppInfoHelper.VERSION);
  }

  @Test
  public void testGetAppVersion3() throws Exception {
    Mockito.when(fileHelperMock.getContentFromResource(AppInfoHelper.VERSION))
        .thenThrow(new IOException("some-io-error"));
    unitUnderTest.getAppVersion();
    Mockito.verify(fileHelperMock, Mockito.times(1)).getContentFromResource(AppInfoHelper.VERSION);
  }

}