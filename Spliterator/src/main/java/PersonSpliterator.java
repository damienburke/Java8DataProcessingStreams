import model.ImmutablePerson;
import model.Person;

import java.util.Spliterator;
import java.util.function.Consumer;

public class PersonSpliterator implements Spliterator<Person> {

  Spliterator<String> linesSpliterator;
  private String name;
  private int age;
  private String city;

  public PersonSpliterator(final Spliterator<String> linesSpliterator) {

    this.linesSpliterator = linesSpliterator;
  }

  @Override
  public boolean tryAdvance(final Consumer<? super Person> action) {

    if (linesSpliterator.tryAdvance(line -> name = line) &&
        linesSpliterator.tryAdvance(line -> city = line) &&
        linesSpliterator.tryAdvance(line -> age = Integer.parseInt(line))) {
      final Person p = ImmutablePerson.builder().city(city).name(name).age(age).build();
      action.accept(p);
      return true;
    }

    return false;
  }

  @Override
  public Spliterator<Person> trySplit() {
    return null;
  }

  @Override
  public long estimateSize() {
    return linesSpliterator.estimateSize() / 3;
  }

  @Override
  public int characteristics() {
    return linesSpliterator.characteristics();
  }
}
