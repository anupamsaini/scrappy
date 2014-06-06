package com.scrappy.sites;

import com.anupams.protogen.Scrape.Element;
import com.anupams.protogen.Scrape.Relative;
import com.anupams.protogen.Scrape.Step;
import com.anupams.protogen.Scrape.Element.By;
import com.anupams.protogen.Scrape.Step.Type;

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
   * Generates a new {@link Step}.
   *
   * @param stepType identifies the {@link Type} of a step
   * @param element the optional {@link Element} to be scraped from DOM
   * @return a generated step
   */
  public Step buildStep(Type stepType, Element element);

  /**
   * Generates a new {@link Step} with a list of {@link Element} .
   *
   * @param stepType identifies the {@link Type} of a step
   * @param elements the elements part of this step
   * @return a generated step
   */
  public Step buildStep(Type stepType, List<Element> elements);

  /**
   * Adds an {@link Element} to a {@link Step}.
   *
   * @param existingStep the existing step to add to
   * @param elementToAdd the element to be added
   * @return the updated step
   */
  public Step addElementToStep(Step existingStep, Element elementToAdd);

  /**
   * Adds a list of {@link Element} to a {@link Step}.
   *
   * @param existingStep the existing step to add to
   * @param elementsToAdd the list of elements to be added
   * @return the updated step
   */
  public Step addElementsToStep(Step existingStep, List<Element> elementsToAdd);

  /**
   * Constructs a list of steps needed to scrape the site under consideration.
   *
   * @return list of steps to be scraped
   */
  public abstract List<Step> constructConfig();
}
