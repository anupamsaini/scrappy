package com.scrappy.sites;

import com.google.common.collect.Lists;

import com.anupams.protogen.Scrape.Element;
import com.anupams.protogen.Scrape.Element.By;
import com.anupams.protogen.Scrape.Relative;
import com.anupams.protogen.Scrape.Step.Type;

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
  private List<Element> elements = Lists.newArrayList();
  private BaseConfig baseConfig;

  @Override
  @Before
  protected void setUp() throws Exception {
    baseConfig = new BaseConfig();
    relatives.add(TestConstants.RELATIVE_ELEMENT.build());
    elements.add(TestConstants.ELEMENT_WITH_RELATIVE.build());
  }

  @Override
  @After
  public void tearDown() {
    relatives.clear();
    elements.clear();
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
  public void testaddRelativesToElement_emptyRelativeList() {
    relatives.clear();
    assertEquals(TestConstants.ELEMENT_USED_AS_PARENT.build(),
        baseConfig.addRelativesToElement(TestConstants.ELEMENT_USED_AS_PARENT.build(), relatives));
  }

  @Test
  public void testaddRelativesToElement_withRelativeList() {
    assertEquals(TestConstants.ELEMENT_WITH_RELATIVE.build(),
        baseConfig.addRelativesToElement(TestConstants.ELEMENT_USED_AS_PARENT.build(), relatives));
  }

  @Test
  public void testbuildStep_withElement() {
    assertEquals(TestConstants.STEP.build(),
        baseConfig.buildStep(Type.SCRAPE, TestConstants.ELEMENT_WITH_RELATIVE.build()));
  }

  @Test
  public void testbuildStep_navigateWithNullElement() {
    assertEquals(TestConstants.STEP.clear().setStepType(Type.NAVIGATE).build(),
        baseConfig.buildStep(Type.NAVIGATE, (Element) null));
  }

  @Test
  public void testbuildStep_failScrapeStepWithNullElement() {
    try {
      assertEquals(TestConstants.STEP.build(), baseConfig.buildStep(Type.SCRAPE, (Element) null));
      fail();
    } catch (IllegalStateException e) {
      assertEquals(SitesConstants.SCRAPE_STEP_WITHOUT_ELEMENT, e.getMessage());
      assertEquals(IllegalStateException.class, e.getClass());
    }
  }

  @Test
  public void testbuildStep_failScrapeStepWithEmptyElementList() {
    elements.clear();
    try {
      assertEquals(TestConstants.STEP.build(), baseConfig.buildStep(Type.SCRAPE, elements));
      fail();
    } catch (IllegalStateException e) {
      assertEquals(SitesConstants.SCRAPE_STEP_WITHOUT_ELEMENT, e.getMessage());
      assertEquals(IllegalStateException.class, e.getClass());
    }
  }
}
