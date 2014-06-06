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
  public Element addRelativeElements(Element existingElement, Relative... relatives) {
    if (null != relatives) {
      return existingElement.toBuilder().addAllRelativeElement(Arrays.asList(relatives)).build();
    }
    return existingElement;
  }

  @Override
  public Element addRelativeElements(Element existingElement, List<Relative> relatives) {
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
  public Element buildElement(String id, By searchElementBy, String value,
      List<Relative> relatives) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(id), "Id can not be null");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(value), "value can not be null");

    Element.Builder element = Element.newBuilder();
    element.setId(id).setSearchBy(searchElementBy).setValue(value);
    if (null != relatives && !relatives.isEmpty()) {
      element.addAllRelativeElement(relatives);
    }
    return element.build();
  }

  @Override
  public Element buildElement(String id, By searchElementBy, String value, Relative... relatives) {
    List<Relative> relations = Lists.newArrayList();
    if (null != relatives) {
      relations = Arrays.asList(relatives);
    }
    return buildElement(id, searchElementBy, value, relations);
  }

  @Override
  public List<Step> constructConfig() {
    throw new IllegalStateException(" Method not implemented");
  }

  @Override
  public Step buildStep(Type stepType, Element element) {
    Preconditions.checkNotNull(stepType, "Step type can not be null.");
    Preconditions.checkNotNull(element, "Element can not be null.");
    return Step.newBuilder().addElement(element).setStepType(stepType).build();

  }


  @Override
  public Step buildStep(Type stepType, List<Element> elements) {
    Preconditions.checkNotNull(stepType, "Step type can not be null.");
    Preconditions.checkNotNull(elements, "Element list can not be null.");
    Step.Builder stepBuilder = Step.newBuilder();
    if (!elements.isEmpty()) {
      stepBuilder.addAllElement(elements);
    }
    return stepBuilder.setStepType(stepType).build();
  }

  @Override
  public Step addElementToStep(Step existingStep, Element elementToAdd) {
    Preconditions.checkNotNull(existingStep, "Existing step can not be null.");
    if (null != elementToAdd) {
      return existingStep.toBuilder().addElement(elementToAdd).build();
    }
    return existingStep;
  }

  @Override
  public Step addElementsToStep(Step existingStep, List<Element> elementsToAdd) {
    Preconditions.checkNotNull(existingStep, "Existing step can not be null.");
    if (null != elementsToAdd && !elementsToAdd.isEmpty()) {
      return existingStep.toBuilder().addAllElement(elementsToAdd).build();
    }
    return existingStep;
  }
}
