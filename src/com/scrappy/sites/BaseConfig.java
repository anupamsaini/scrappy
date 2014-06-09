package com.scrappy.sites;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.anupams.protogen.Scrape.Element;
import com.anupams.protogen.Scrape.Element.By;
import com.anupams.protogen.Scrape.Relative;
import com.anupams.protogen.Scrape.Step;
import com.anupams.protogen.Scrape.Step.Type;

import java.util.Arrays;
import java.util.List;

/**
 * Base implementation class for generating {@link Element} and {@link Step}.
 */
public class BaseConfig implements Config {

  @Override
  public Element addRelativeToElement(Element existingElement, Relative relative) {
    if (null != relative) {
      return existingElement.toBuilder().addAllRelativeElement(Arrays.asList(relative)).build();
    }
    return existingElement;
  }

  @Override
  public Element addRelativesToElement(Element existingElement, List<Relative> relatives) {
    if (null != relatives && !relatives.isEmpty()) {
      return existingElement.toBuilder().addAllRelativeElement(relatives).build();
    }
    return existingElement;
  }

  @Override
  public Element buildElement(String id, By searchElementBy, String value) {
    return buildElement(id, searchElementBy, value, Lists.<Relative>newArrayList());
  }

  @Override
  public Element buildElement(String id, By searchElementBy, String value, Relative relative) {
    List<Relative> relations = Lists.newArrayList();
    if (null != relative) {
      relations = Arrays.asList(relative);
    }
    return buildElement(id, searchElementBy, value, relations);
  }

  @Override
  public Element buildElement(String id, By searchElementBy, String value,
      List<Relative> relatives) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(id), SitesConstants.ID_MANDATORY);
    Preconditions.checkArgument(!Strings.isNullOrEmpty(value), SitesConstants.VALUE_MANDATORY);

    Element.Builder element = Element.newBuilder();
    element.setId(id).setSearchBy(searchElementBy).setValue(value);
    if (null != relatives && !relatives.isEmpty()) {
      element.addAllRelativeElement(relatives);
    }
    return element.build();
  }

  @Override
  public Step addElementToStep(Step existingStep, Element elementToAdd) {
    Preconditions.checkNotNull(existingStep, SitesConstants.STEP_MANDATORY);
    Preconditions.checkNotNull(existingStep, SitesConstants.ELEMENT_MANDATORY);
    return existingStep.toBuilder().addElement(elementToAdd).build();
  }

  @Override
  public Step addElementsToStep(Step existingStep, List<Element> elementsToAdd) {
    Preconditions.checkNotNull(existingStep, SitesConstants.STEP_MANDATORY);
    if (null != elementsToAdd && !elementsToAdd.isEmpty()) {
      return existingStep.toBuilder().addAllElement(elementsToAdd).build();
    }
    return existingStep;
  }

  @Override
  public Step buildStep(Type stepType, Element element) {
    Preconditions.checkNotNull(stepType, SitesConstants.STEP_TYPE_MANDATORY);
    Step.Builder step = Step.newBuilder();
    if (stepType == Type.SCRAPE && null == element) {
      throw new IllegalStateException(SitesConstants.SCRAPE_STEP_WITHOUT_ELEMENT);
    } else if (null != element) {
      step.addElement(element);
    }
    return step.setStepType(stepType).build();

  }

  @Override
  public Step buildStep(Type stepType, List<Element> elements) {
    Preconditions.checkNotNull(stepType, SitesConstants.STEP_TYPE_MANDATORY);
    Preconditions.checkNotNull(elements, SitesConstants.ELEMENT_MANDATORY);
    if (stepType == Type.SCRAPE && elements.isEmpty()) {
      throw new IllegalStateException(SitesConstants.SCRAPE_STEP_WITHOUT_ELEMENT);
    }
    return Step.newBuilder().setStepType(stepType).addAllElement(elements).build();
  }

  @Override
  public List<Step> constructConfig() {
    throw new IllegalStateException(" Method not implemented");
  }
}
