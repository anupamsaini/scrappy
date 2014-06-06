package com.scrappy.sites;

import com.google.common.collect.Lists;

import com.anupams.protogen.Scrape.Element.By;
import com.anupams.protogen.Scrape.Relative;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.List;

/**
 * Unit test for {@link BaseConfig}.
 */
public class BaseConfigTest extends TestCase {


  private List<Relative> relatives;
  private BaseConfig baseConfig;

  @Override
  protected void setUp() throws Exception {
    relatives = Lists.newArrayList();
    baseConfig = new BaseConfig();
    relatives.add(TestConstants.RELATIVE_ELEMENT.build());
  }

  @Test
  public void testBuildElement_success() {
    assertEquals(TestConstants.ELEMENT_WITH_RELATIVE.build(),
        baseConfig.buildElement(TestConstants.PARENT_ID, By.ID, TestConstants.DUMMY, relatives));
  }
}
