import model.Person;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.nio.file.Files.lines;

public class CreatingSpliterator {

  public static void main(final String[] args) {

    final String resourceName = "files/people.txt";
    
    try (Stream<String> lines = lines(Paths.get(ClassLoader.getSystemResource(resourceName).toURI()))) {

      final Spliterator<String> linesSpliterator = lines.spliterator();

      final Spliterator<Person> personSpliterator = new PersonSpliterator(linesSpliterator);

      final Stream<Person> people = StreamSupport.stream(personSpliterator, false);

      people.forEach(System.out::println);

    } catch (final IOException e) {
      e.printStackTrace();
    } catch (final URISyntaxException e) {
      e.printStackTrace();
    }


  }

}
