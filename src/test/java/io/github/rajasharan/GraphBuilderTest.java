package io.github.rajasharan;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by raja on 12/10/17.
 */
public class GraphBuilderTest {
    private GraphBuilder subject;

    @Before
    public void setup() {
        subject = new GraphBuilder();
    }

    @Test
    public void testGraphNotEmpty() {
        subject.addBidirectional("A", "B", 4);
        subject.addBidirectional("B", "C", 3);
        subject.addBidirectional("A", "C", 2);

        Assertions.assertThat(subject.getGraph().keySet()).containsOnly("A", "B", "C");
    }

    @Test
    public void testSameWeightsAreAllowed() {
        subject.addBidirectional("A", "B", 3);
        subject.addBidirectional("A", "C", 3);

        Assertions.assertThat(subject.getGraph().get("A")).hasSize(2);
    }

    @Test
    public void testShortestDistanceEx1() {
        subject.addBidirectional("START", "A", 7);
        subject.addBidirectional("START", "B", 3);
        subject.addBidirectional("B", "A", 2);
        subject.addBidirectional("A", "END", 4);

        Path path = subject.shortestPath("START", "END");

        Assertions.assertThat(path.getTotalDistance()).isEqualTo(9);
        Assertions.assertThat(path.toString().split("\\s+")).containsExactly("START", "B", "A", "END");
    }

    @Test
    public void testInvalidShortestDistance() {
        subject.addBidirectional("A", "B", 3);
        subject.addBidirectional("B", "C", 2);

        Path path = subject.shortestPath("A", "c");

        Assertions.assertThat(path).isNull();
    }
}