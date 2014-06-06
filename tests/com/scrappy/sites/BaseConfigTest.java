package com.scrappy.sites;

import com.google.common.collect.Lists;

import com.anupams.protogen.Scrape.Element.By;
import com.anupams.protogen.Scrape.Relative;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for {@link BaseConfig}.
 */
public class BaseConfigTest extends TestCase {


  private List<Relative> relatives = Lists.newArrayList();
  private BaseConfig baseConfig;

  @Override
  @Before
  protected void setUp() throws Exception {
    baseConfig = new BaseConfig();
    relatives.add(TestConstants.RELATIVE_ELEMENT.build());
  }

  @Override
  @After
  public void tearDown() {
    relatives.clear();
  }

  @Test
  public void testBuildElement_withRelativeList() {
    assertEquals(TestConstants.ELEMENT_WITH_RELATIVE.build(),
        baseConfig.buildElement(TestConstants.PARENT_ID, By.ID, TestConstants.DUMMY, relatives));
  }

  @Test
  public void testBuildElement_emptyRelativeList() {
    relatives.clear();
    assertEquals(TestConstants.ELEMENT_USED_AS_PARENT.build(),
        baseConfig.buildElement(TestConstants.PARENT_ID, By.ID, TestConstants.DUMMY, relatives));
  }

  @Test
  public void testaddRelativeElement_emptyRelativeList() {
    relatives.clear();
    assertEquals(TestConstants.ELEMENT_USED_AS_PARENT.build(),
        baseConfig.addRelativeElements(TestConstants.ELEMENT_USED_AS_PARENT.build(), relatives));
  }

  @Test
  public void testaddRelativeElement_withRelativeList() {
    assertEquals(TestConstants.ELEMENT_WITH_RELATIVE.build(),
        baseConfig.addRelativeElements(TestConstants.ELEMENT_USED_AS_PARENT.build(), relatives));
  }
}
