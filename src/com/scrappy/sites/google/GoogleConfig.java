package com.scrappy.sites.google;

import com.google.common.collect.Lists;

import com.anupams.protogen.Scrape.Element;
import com.anupams.protogen.Scrape.Element.By;
import com.anupams.protogen.Scrape.Step;
import com.anupams.protogen.Scrape.Step.Type;
import com.scrappy.sites.BaseConfig;

import java.util.List;

/**
 * TODO: Insert description here. (generated by anupams)
 */
public class GoogleConfig extends BaseConfig {

  private Step landingPage() {
    Step.Builder stepBuilder = Step.newBuilder();
    Element element =
    buildElement("id_gbqfsa", By.ID, "gbqfsa", null);
    return stepBuilder.addElement(element).setStepType(Type.SCRAPE).build();
  }

  @Override
  public List<Step> constructConfig() {
    List<Step> steps = Lists.newArrayList();
    steps.add(landingPage());
    return steps;
  }
}