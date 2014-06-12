package com.scrappy.sites;

import com.scrappy.protogen.Scrape.Element;
import com.scrappy.protogen.Scrape.Element.By;
import com.scrappy.protogen.Scrape.Relative;
import com.scrappy.protogen.Scrape.Scraper;
import com.scrappy.protogen.Scrape.Step;
import com.scrappy.protogen.Scrape.Step.Type;

import java.util.List;

/**
 * The {@link ScrapeConfig} interface provides methods for constructing a {@link Step} along with
 * its constituents.
 */
public interface ScrapeConfig {

  /**
   * Generates a {@link Scraper}.
   *
   * @param scraperName the scraper's name
   * @param steps the scrape steps
   * @return the generated scraper
   */
  public Scraper buildScraper(String scraperName, List<Step> steps);

  /**
   * Generates an {@link Element} to be scraped.
   *
   * @param searchElementBy specifies they way dom would be queried, i.e through CSS,ID,XPATH etc
   * @param value
   * @return the generated element
   */
  public Element buildElement(String searchElementBy, String value);

  /**
   * Generates an {@link Element} to be scraped.
   *
   * @param searchElementBy specifies they way dom would be queried, i.e through CSS,ID,XPATH etc
   * @param value the value to be looked up in the dom
   * @return the generated element
   */
  public Element buildElement(By searchElementBy, String value);

  /**
   * Generates an {@link Element} to be scraped.
   *
   * @param searchElementBy specifies they way dom would be queried, i.e through CSS,ID,XPATH etc
   * @param value the value to be looked up in the dom
   * @param relatives a relative element to be searched as child of the element being built
   * @return the generated element
   */
  public Element buildElement(By searchElementBy, String value, Relative relatives);

  /**
   * Generates an {@link Element} to be scraped.
   *
   * @param searchElementBy specifies they way dom would be queried, i.e through CSS,ID,XPATH etc
   * @param value the value to be looked up in the dom
   * @param relatives a list of elements to be searched as child of the element being built
   * @return the generated element
   */
  public Element buildElement(By searchElementBy, String value, List<Relative> relatives);

  /**
   * Adds {@link Relative} elements to an existing element.
   *
   * @param existingElement the element to which relative elements are to be added
   * @param relative a relative element to be added
   * @return the updated element
   */
  public Element addRelativeToElement(Element existingElement, Relative relative);

  /**
   * Adds {@link Relative} elements to an existing element.
   *
   * @param existingElement the element to which relative elements are to be added
   * @param relatives a list of relatives to be added
   * @return the updated element
   */
  public Element addRelativesToElement(Element existingElement, List<Relative> relatives);

  /**
   * Generates a new {@link Step}.
   *
   * @param stepType identifies the {@link Type} of a step
   * @param element the optional {@link Element} to be scraped from DOM
   * @return a generated step
   */
  public Step buildStep(String stepType, Element element, String callback);

  /**
   * Generates a new {@link Step}.
   *
   * @param stepType identifies the {@link Type} of a step
   * @param element the optional {@link Element} to be scraped from DOM
   * @return a generated step
   */
  public Step buildStep(Type stepType, Element element, String callback);

  /**
   * Generates a new {@link Step} with a list of {@link Element} .
   *
   * @param stepType identifies the {@link Type} of a step
   * @param elements the elements part of this step
   * @return a generated step
   */
  public Step buildStep(String stepType, List<Element> elements, String callback);

  /**
   * Generates a new {@link Step} with a list of {@link Element} .
   *
   * @param stepType identifies the {@link Type} of a step
   * @param elements the elements part of this step
   * @return a generated step
   */
  public Step buildStep(Type stepType, List<Element> elements, String callback);

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
   * Generates a new {@link Relative}.
   *
   * @param parentId the parent elements id
   * @param element the element to be scrapped.
   * @return the element to be scraped in relation
   */
  public Relative buildRelative(String parentId, Element element);

  /**
   * Generates a new {@link Relative}.
   *
   * @param parentId the parent elements id
   * @param elements the elements to be scrapped.
   * @return the element to be scraped in relation
   */
  public Relative buildRelative(String parentId, List<Element> elements);

  /**
   * Adds an element to be scraped to relative container.
   *
   * @param existingRelative the relative element to search
   * @param elementToAdd the element to be added to relative container
   * @return the updated relative
   */
  public Relative addElememtToRelative(Relative existingRelative, Element elementToAdd);

  /**
   * Adds an element to be scraped to relative container.
   *
   * @param existingRelative the relative element to search
   * @param elementsToAdd the elements to be added to relative container
   * @return the updated relative
   */
  public Relative addElememtsToRelative(Relative existingRelative, List<Element> elementsToAdd);

  /**
   * Constructs a list of steps needed to scrape the site under consideration.
   *
   * @return list of steps to be scraped
   */
  public abstract List<Step> constructConfig();
}
