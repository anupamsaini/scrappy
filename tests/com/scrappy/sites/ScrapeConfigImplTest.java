package com.scrappy.sites;

import com.google.common.collect.Lists;

import com.scrappy.protogen.Scrape.Element;
import com.scrappy.protogen.Scrape.Element.By;
import com.scrappy.protogen.Scrape.Relative;
import com.scrappy.protogen.Scrape.Step.Type;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for {@link ScrapeConfigImpl}.
 */
public class ScrapeConfigImplTest extends TestCase {

  private List<Relative> relatives = Lists.newArrayList();
  private List<Element> elements = Lists.newArrayList();
  private ScrapeConfigImpl baseConfig;

  @Override
  @Before
  protected void setUp() throws Exception {
    baseConfig = new ScrapeConfigImpl();
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
  public void testBuildElement_withStrings() {
    assertEquals(TestConstants.ELEMENT_USED_AS_PARENT.build(),
        baseConfig.buildElement(By.ID.name(), TestConstants.DUMMY));
  }

  @Test
  public void testBuildElement_failWithStrings() {
    try {
      baseConfig.buildElement(TestConstants.DUMMY, TestConstants.DUMMY);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(
          (e.getMessage().contains((Lists.newArrayList(By.class.getEnumConstants()).toString()))));
    }
  }

  @Test
  public void testBuildElement_withRelativeList() {
    assertEquals(TestConstants.ELEMENT_WITH_RELATIVE.build(),
        baseConfig.buildElement(By.ID, TestConstants.DUMMY, relatives));
  }

  @Test
  public void testBuildElement_emptyRelativeList() {
    relatives.clear();
    assertEquals(TestConstants.ELEMENT_USED_AS_PARENT.build(),
        baseConfig.buildElement(By.ID, TestConstants.DUMMY, relatives));
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
  public void testBuildStep_withStrings() {
    assertEquals(TestConstants.SCRAPE_STEP.build(), baseConfig.buildStep(Type.SCRAPE.name(),
        TestConstants.ELEMENT_WITH_RELATIVE.build(), TestConstants.DUMMY));
  }

  @Test
  public void testBuildStep_failWithStrings() {
    try {
      baseConfig.buildStep(TestConstants.DUMMY, TestConstants.ELEMENT_WITH_RELATIVE.build(),
          TestConstants.DUMMY);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue((e.getMessage().contains(
          (Lists.newArrayList(Type.class.getEnumConstants()).toString()))));
    }
  }

  @Test
  public void testbuildStep_withElement() {
    assertEquals(TestConstants.SCRAPE_STEP.build(), baseConfig.buildStep(Type.SCRAPE,
        TestConstants.ELEMENT_WITH_RELATIVE.build(), TestConstants.DUMMY));
  }

  @Test
  public void testbuildStep_navigateWithNullElement() {
    assertEquals(TestConstants.NAVIGATE_STEP.build(),
        baseConfig.buildStep(Type.NAVIGATE, (Element) null, TestConstants.DUMMY));
  }

  @Test
  public void testbuildStep_failScrapeStepWithNullElement() {
    try {
      assertEquals(TestConstants.SCRAPE_STEP.build(),
          baseConfig.buildStep(Type.SCRAPE, (Element) null, TestConstants.DUMMY));
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
      assertEquals(TestConstants.SCRAPE_STEP.build(),
          baseConfig.buildStep(Type.SCRAPE, elements, TestConstants.DUMMY));
      fail();
    } catch (IllegalStateException e) {
      assertEquals(SitesConstants.SCRAPE_STEP_WITHOUT_ELEMENT, e.getMessage());
      assertEquals(IllegalStateException.class, e.getClass());
    }
  }
}
