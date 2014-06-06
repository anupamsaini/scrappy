package com.scrappy.sites.google;

import com.google.common.collect.Lists;

import com.anupams.protogen.Scrape.Element;
import com.anupams.protogen.Scrape.Element.By;
import com.anupams.protogen.Scrape.Step;
import com.anupams.protogen.Scrape.Step.Type;
import com.scrappy.sites.BaseConfig;
import com.scrappy.sites.Config;

import java.util.List;

/**
 * Scrape {@link Step} configuration class for <a href="http://google.com">http://google.com</a>
 */
public class GoogleConfig extends BaseConfig implements Config {

  private Step landingPage() {
    Step.Builder stepBuilder = Step.newBuilder();
    Element element = buildElement("id_gbqfsa", By.ID, "gbqfsa");
    return stepBuilder.addElement(element).setStepType(Type.SCRAPE).build();
  }

  @Override
  public List<Step> constructConfig() {
    List<Step> steps = Lists.newArrayList();
    steps.add(landingPage());
    return steps;
  }
}
