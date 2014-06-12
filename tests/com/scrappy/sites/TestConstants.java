package com.scrappy.sites;

import com.scrappy.protogen.Scrape.Element;
import com.scrappy.protogen.Scrape.Element.By;
import com.scrappy.protogen.Scrape.Relative;
import com.scrappy.protogen.Scrape.Step;
import com.scrappy.protogen.Scrape.Step.Type;

/**
 * Constants to be used for unit testing of sites package.
 */
public interface TestConstants {


  public static final String DUMMY = "dummy";
  public static final String ID_SUB_PART = DUMMY + "_" + By.ID.name();
  public static final String PARENT_ID = By.ID.name().toLowerCase() + "_" + DUMMY;
  public static final String CHILD_ID = By.ID.name().toLowerCase() + "_" + DUMMY;

  /**
   * Used as the root element to be searched in DOM.
   */
  public static final Element.Builder ELEMENT_USED_AS_PARENT =
      Element.newBuilder().setSearchBy(By.ID).setValue(DUMMY).setId(PARENT_ID);

  /**
   * Used as a child element searched in relation to parent element.
   */
  public static final Element.Builder ELEMENT_USED_AS_CHILD =
      Element.newBuilder().setSearchBy(By.ID).setValue(DUMMY).setId(CHILD_ID);

  /**
   * Place holder for an element to be searched in relation to a root element.
   */
  public static final Relative.Builder RELATIVE_ELEMENT =
      Relative.newBuilder().setParentId(PARENT_ID).addElement(ELEMENT_USED_AS_CHILD.build());

  /**
   * A root element containing an element to be searched as its relative.
   */
  public static final Element.Builder ELEMENT_WITH_RELATIVE =
      ELEMENT_USED_AS_PARENT.build().toBuilder().addRelativeElement(RELATIVE_ELEMENT);

  public static final Step.Builder SCRAPE_STEP = Step.newBuilder().setStepType(Type.SCRAPE)
      .addElement(ELEMENT_WITH_RELATIVE).setCallbackClass(DUMMY);

  public static final Step.Builder NAVIGATE_STEP =
      Step.newBuilder().setStepType(Type.NAVIGATE).setCallbackClass(DUMMY);

}
