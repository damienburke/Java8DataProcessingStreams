package model;

import org.immutables.value.Value;

@Value.Immutable
public interface Person {

  String name();

  String city();

  int age();
}
