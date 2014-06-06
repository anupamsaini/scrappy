package com.scrappy.sites;

import com.anupams.protogen.Scrape.Element;
import com.anupams.protogen.Scrape.Relative;
import com.anupams.protogen.Scrape.Step;
import com.anupams.protogen.Scrape.Element.By;

import java.util.List;

/**
 * The {@link Config} interface provides methods for constructing a {@link Step} along with its
 * constituents.
 */
public interface Config {

  /**
   * Adds {@link Relative} elements to an existing element.
   *
   * @param existingElement the element to which relative elements are to be added
   * @param relatives an array of relatives to be added
   * @return the updated element
   */
  public Element addRelativeElements(Element existingElement, Relative... relatives);

  /**
   * Adds {@link Relative} elements to an existing element.
   *
   * @param existingElement the element to which relative elements are to be added
   * @param relatives a list of relatives to be added
   * @return the updated element
   */
  public Element addRelativeElements(Element existingElement, List<Relative> relatives);

  /**
   * Generates an {@link Element} to be scraped.
   *
   * @param id the unique identifier of the element in configuration
   * @param searchElementBy specifies they way dom would be queried, i.e through CSS,ID,XPATH etc
   * @param value the value to be looked up in the dom
   * @return the generated element
   */
  public Element buildElement(String id, By searchElementBy, String value);

  /**
   * Generates an {@link Element} to be scraped.
   *
   * @param id the unique identifier of the element in configuration
   * @param searchElementBy specifies they way dom would be queried, i.e through CSS,ID,XPATH etc
   * @param value the value to be looked up in the dom
   * @param relatives a list of elements to be searched as child of the element being built
   * @return the generated element
   */
  public Element buildElement(String id, By searchElementBy, String value,
      List<Relative> relatives);

  /**
   * Generates an {@link Element} to be scraped.
   *
   * @param id the unique identifier of the element in configuration
   * @param searchElementBy specifies they way dom would be queried, i.e through CSS,ID,XPATH etc
   * @param value the value to be looked up in the dom
   * @param relatives an array of elements to be searched as child of the element being built
   * @return the generated element
   */
  public Element buildElement(String id, By searchElementBy, String value, Relative... relatives);

  /**
   * Constructs a list of steps needed to scrape the site under consideration.
   *
   * @return list of steps to be scraped
   */
  public abstract List<Step> constructConfig();
}
