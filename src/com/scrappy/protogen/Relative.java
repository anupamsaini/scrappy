package com.scrappy.protogen;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * The elements that is to be searched relative to a parent.
 */
public final class Relative {


  public final String parent_id;
  public final List<Element> element;

  public Relative(String parent_id, List<Element> element) {
    this.parent_id = parent_id;
    this.element = ImmutableList.copyOf(element);
  }

  private Relative(Builder builder) {
    this(builder.parent_id, builder.element);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Relative)) {
      return false;
    }
    Relative o = (Relative) other;
    return Objects.equal(parent_id, o.parent_id) && Objects.equal(element, o.element);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(parent_id, element);
  }

  public static final class Builder {

    public String parent_id;
    public List<Element> element;

    public Builder() {}

    public Builder(Relative message) {
      if (message == null) {
        return;
      }
      this.parent_id = message.parent_id;
      this.element = Lists.newArrayList(message.element);
    }

    public Builder parent_id(String parent_id) {
      this.parent_id = parent_id;
      return this;
    }

    public Builder element(List<Element> element) {
      this.element = element;
      return this;
    }

    public Relative build() {
      return new Relative(this);
    }
  }
}
