package com.scrappy.sites;

import com.anupams.protogen.Scrape.Element;
import com.anupams.protogen.Scrape.Element.By;
import com.anupams.protogen.Scrape.Relative;

/**
 * Constants to be used for unit testing of sites package.
 */
public interface TestConstants {


  public static final String DUMMY = "dummy";
  public static final String ID_SUB_PART = DUMMY + "_" + By.ID.name();
  public static final String PARENT_ID = "parent_" + ID_SUB_PART;
  public static final String CHILD_ID = "child_" + ID_SUB_PART;

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
      Relative.newBuilder().setParentId(PARENT_ID).addElements(ELEMENT_USED_AS_CHILD.build());

  /**
   * A root element containing an element to be searched as its relative.
   */
  public static final Element.Builder ELEMENT_WITH_RELATIVE =
      ELEMENT_USED_AS_PARENT.addRelativeElement(RELATIVE_ELEMENT);

}
