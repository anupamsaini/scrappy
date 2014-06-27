package com.scrappy.protogen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

public final class Scraper {

  public final String scraper_name;

  public final List<Step> step;

  public Scraper(String scraper_name, List<Step> step) {
    this.scraper_name = scraper_name;
    this.step = ImmutableList.copyOf(step);
  }

  private Scraper(Builder builder) {
    this(builder.scraper_name, builder.step);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Scraper)) {
      return false;
    }
    Scraper o = (Scraper) other;
    return Objects.equals(scraper_name, o.scraper_name) && Objects.equals(step, o.step);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scraper_name, step);
  }

  public static final class Builder {

    public String scraper_name;
    public List<Step> step;

    public Builder() {}

    public Builder(Scraper message) {
      if (message == null) {
        return;
      }
      this.scraper_name = message.scraper_name;
      this.step = Lists.newArrayList(message.step);
    }

    public Builder scraper_name(String scraper_name) {
      this.scraper_name = scraper_name;
      return this;
    }

    public Builder step(List<Step> step) {
      this.step = step;
      return this;
    }

    public Scraper build() {
      return new Scraper(this);
    }
  }
}
