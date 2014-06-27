package com.scrappy.protogen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

/**
 * A scraper can at any given point of time either scrape or navigate to a page. Step is a top level
 * abstraction needed by the framework to determine if it has to scrape or navigate to a new page or
 * make an ajax call.
 */
public final class Step {

  public final Type step_type;

  /**
   * The DOM element that has the information scraper intends to scrape.
   */
  public final List<Element> element;

  /**
   * Optional class to invoke with the passed webdriver object.
   */
  public final String callback_class;

  public Step(Type step_type, List<Element> element, String callback_class) {
    this.step_type = step_type;
    this.element = ImmutableList.copyOf(element);
    this.callback_class = callback_class;
  }

  private Step(Builder builder) {
    this(builder.step_type, builder.element, builder.callback_class);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Step)) {
      return false;
    }
    Step o = (Step) other;
    return Objects.equals(step_type, o.step_type) && Objects.equals(element, o.element)
        && Objects.equals(callback_class, o.callback_class);
  }

  @Override
  public int hashCode() {
    return Objects.hash(step_type, element, callback_class);

  }

  public static final class Builder {

    public Type step_type;
    public List<Element> element;
    public String callback_class;

    public Builder() {}

    public Builder(Step message) {
      if (message == null) {
        return;
      }
      this.step_type = message.step_type;
      this.element = Lists.newArrayList(message.element);
      this.callback_class = message.callback_class;
    }

    public Builder step_type(Type step_type) {
      this.step_type = step_type;
      return this;
    }

    /**
     * The DOM element that has the information scraper intends to scrape.
     */
    public Builder element(List<Element> element) {
      this.element = element;
      return this;
    }

    /**
     * Optional class to invoke with the passed webdriver object.
     */
    public Builder callback_class(String callback_class) {
      this.callback_class = callback_class;
      return this;
    }

    public Step build() {
      return new Step(this);
    }
  }

  public enum Type {
    NAVIGATE(1), SCRAPE(2), PAGINATED(3);

    private final int value;

    private Type(int value) {
      this.value = value;
    }


    public int getValue() {
      return value;
    }
  }
}
