/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package org.soft.assignment1.lagom.board.impl;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.Jsonable;

/**
 * This interface defines all the events that the Board entity supports.
 * <p>
 * By convention, the events should be inner classes of the interface, which
 * makes it simple to get a complete picture of what events an entity has.
 */
public interface BoardEvent extends Jsonable {

  /**
   * An event that represents a change in greeting message.
   */
  @SuppressWarnings("serial")
  @Immutable
  @JsonDeserialize
  public final class GreetingMessageChanged implements BoardEvent {
    public final String message;

    @JsonCreator
    public GreetingMessageChanged(String message) {
      this.message = Preconditions.checkNotNull(message, "message");
    }

    @Override
    public boolean equals(@Nullable Object another) {
      if (this == another)
        return true;
      return another instanceof GreetingMessageChanged && equalTo((GreetingMessageChanged) another);
    }

    private boolean equalTo(GreetingMessageChanged another) {
      return message.equals(another.message);
    }

    @Override
    public int hashCode() {
      int h = 31;
      h = h * 17 + message.hashCode();
      return h;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper("GreetingMessageChanged").add("message", message).toString();
    }
  }
  
  
  /**
   * An event that represents the creation of a board.
   */
  @SuppressWarnings("serial")
  @Immutable
  @JsonDeserialize
  public final class BoardCreated implements BoardEvent {
	public final String id;
    public String title;


    @JsonCreator
    public BoardCreated(@JsonProperty("id") String id, @JsonProperty("title") String title) {
      this.id = Preconditions.checkNotNull(id, "id");
      this.title = Preconditions.checkNotNull(title, "title");
    }

    @Override
    public boolean equals(@Nullable Object another) {
      if (this == another)
        return true;
      return another instanceof BoardCreated && equalTo((BoardCreated) another);
    }

    private boolean equalTo(BoardCreated another) {
      return id.equals(another.id);
    }

    @Override
    public int hashCode() {
      int h = 31;
      h = h * 17 + id.hashCode();
      return h;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper("BoardCreated").add("id", id).add("title", title).toString();
    }
  }
  
  
}
