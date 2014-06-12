package com.scrappy.sites;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.protobuf.Message;

import com.scrappy.protogen.Scrape.Element;
import com.scrappy.protogen.Scrape.Element.By;
import com.scrappy.protogen.Scrape.Relative;
import com.scrappy.protogen.Scrape.Scraper;
import com.scrappy.protogen.Scrape.Step;
import com.scrappy.protogen.Scrape.Step.Type;

import java.util.Collection;
import java.util.List;

/**
 * Base implementation class for generating {@link Element} and {@link Step}.
 */
public class ScrapeConfigImpl implements ScrapeConfig {

  /**
   * Returns {@code true} if the given collection is not null or empty.
   *
   * @param messages a collection reference to check
   * @return {@code true} if the collection is null or empty
   */
  private <T extends Collection<? extends Message>> boolean isCollectionNullOrEmpty(T messages) {
    return null == messages || messages.isEmpty() ? true : false;
  }

  @Override
  public Scraper buildScraper(String scraperName, List<Step> steps) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(scraperName), "Scraper name is mandatory.");
    Preconditions.checkNotNull(!isCollectionNullOrEmpty(steps), "Scraper must have steps.");
    return Scraper.newBuilder().setScraperName(scraperName).addAllStep(steps).build();
  }

  /**
   * Converts a string to corresponding enum.
   *
   * @param convertedEnumClass the {@link Class} of enum to convert value to
   * @param toConvert the string to be converted to enum
   * @return the enum representation of the string
   */
  private <E extends Enum<E>> E convertStringToEnum(Class<E> convertedEnumClass, String toConvert) {
    for (E enu : convertedEnumClass.getEnumConstants()) {
      if (enu.name().equalsIgnoreCase(toConvert)) {
        return enu;
      }
    }
    return null;
  }

  @Override
  public Element buildElement(String searchElementBy, String value) {
    By by = convertStringToEnum(By.class, searchElementBy);
    if (null == by) {
      throw new IllegalArgumentException(
          " 'By' value must be one of these : " + Lists.newArrayList(By.class.getEnumConstants()));
    }
    return buildElement(by, value);
  }

  @Override
  public Element buildElement(By searchElementBy, String value) {
    return buildElement(searchElementBy, value, Lists.<Relative>newArrayList());
  }

  @Override
  public Element buildElement(By searchElementBy, String value, Relative relative) {
    return null == relative ? buildElement(searchElementBy, value)
        : buildElement(searchElementBy, value, /* add relative to list */
        Lists.newArrayList(relative));
  }

  @Override
  public Element buildElement(By searchElementBy, String value, List<Relative> relatives) {
    Preconditions.checkNotNull(searchElementBy, SitesConstants.ID_MANDATORY);
    Preconditions.checkArgument(!Strings.isNullOrEmpty(value), SitesConstants.VALUE_MANDATORY);

    // Construct id from by and value.
    String id = searchElementBy.name().toLowerCase() + "_" + value;
    Element.Builder element = Element.newBuilder();
    element.setId(id).setSearchBy(searchElementBy).setValue(value);
    if (!isCollectionNullOrEmpty(relatives)) {
      element.addAllRelativeElement(relatives);
    }
    return element.build();
  }

  @Override
  public Element addRelativeToElement(Element existingElement, Relative relative) {
    return null == relative ? existingElement
        : addRelativesToElement(existingElement, Lists.newArrayList(relative));
  }

  @Override
  public Element addRelativesToElement(Element existingElement, List<Relative> relatives) {
    return isCollectionNullOrEmpty(relatives) ? existingElement
        : existingElement.toBuilder().addAllRelativeElement(relatives).build();
  }

  @Override
  public Step buildStep(String stepType, Element element, String callBackClass) {
    Type stepTypeEnum = convertStringToEnum(Type.class, stepType);
    if (null == stepTypeEnum) {
      throw new IllegalArgumentException(" Step type value must be one of these : "
          + Lists.newArrayList(Type.class.getEnumConstants()));
    }
    return buildStep(stepTypeEnum, element, callBackClass);
  }

  @Override
  public Step buildStep(Type stepType, Element element, String callBackClass) {
    return null == element ? buildStep(stepType, /* empty list */Lists.<Element>newArrayList(),
        callBackClass)
        : buildStep(stepType, Lists.newArrayList(element), callBackClass);
  }

  @Override
  public Step buildStep(String stepType, List<Element> elements, String callback) {
    Type stepTypeEnum = convertStringToEnum(Type.class, stepType);
    if (null == stepTypeEnum) {
      throw new IllegalArgumentException(" Step type value must be one of these : "
          + Lists.newArrayList(Type.class.getEnumConstants()));
    }
    return buildStep(stepTypeEnum, elements, callback);
  }

  @Override
  public Step buildStep(Type stepType, List<Element> elements, String callBackClass) {
    Preconditions.checkNotNull(stepType, SitesConstants.STEP_TYPE_MANDATORY);
    if (isCollectionNullOrEmpty(elements)) {
      // Step other than navigate requires elements to scrape.
      if (stepType != Type.NAVIGATE) {
        throw new IllegalStateException(SitesConstants.SCRAPE_STEP_WITHOUT_ELEMENT);
      } else if (stepType != Type.NAVIGATE && Strings.isNullOrEmpty(callBackClass)) {
        // Callback is needed for steps other than navigate.
        throw new IllegalStateException(" Scrape step without callback class.");
      }
      return Step.newBuilder().setStepType(stepType)
          .setCallbackClass(Strings.nullToEmpty(callBackClass)).build();
    }
    return Step
        .newBuilder()
        .setStepType(stepType)
        .addAllElement(elements)
        .setCallbackClass(Strings.nullToEmpty(callBackClass))
        .build();
  }

  @Override
  public Step addElementToStep(Step existingStep, Element elementToAdd) {
    return null == elementToAdd ? existingStep
        : addElementsToStep(existingStep, Lists.newArrayList(elementToAdd));
  }

  @Override
  public Step addElementsToStep(Step existingStep, List<Element> elementsToAdd) {
    Preconditions.checkNotNull(existingStep, SitesConstants.STEP_MANDATORY);
    Preconditions.checkNotNull(elementsToAdd, "The collection must not be null");
    elementsToAdd.addAll(existingStep.getElementList());

    return buildStep(existingStep.getStepType(), elementsToAdd, existingStep.getCallbackClass());
  }

  @Override
  public Relative buildRelative(String parentId, Element element) {
    return null == element ? buildRelative(parentId, /* empty list */Lists.<Element>newArrayList())
        : buildRelative(parentId, Lists.newArrayList(element));
  }

  @Override
  public Relative buildRelative(String parentId, List<Element> elements) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(parentId), "Parent id must in relative.");
    Preconditions.checkNotNull(!isCollectionNullOrEmpty(elements),
        "Element must be present in relative container.");
    return Relative.newBuilder().setParentId(parentId).addAllElement(elements).build();
  }

  @Override
  public Relative addElememtToRelative(Relative existingRelative, Element elementToAdd) {
    return null == elementToAdd ? existingRelative
        : addElememtsToRelative(existingRelative, Lists.newArrayList(elementToAdd));
  }

  @Override
  public Relative addElememtsToRelative(Relative existingRelative, List<Element> elementsToAdd) {
    Preconditions.checkNotNull(existingRelative, "Reltive element needed.");
    Preconditions.checkNotNull(!isCollectionNullOrEmpty(existingRelative.getElementList()),
        "Element must be present in relative container.");
    return isCollectionNullOrEmpty(elementsToAdd) ? existingRelative
        : existingRelative.toBuilder().addAllElement(elementsToAdd).build();
  }

  @Override
  public List<Step> constructConfig() {
    throw new IllegalStateException("Method not implemented");
  }
}
