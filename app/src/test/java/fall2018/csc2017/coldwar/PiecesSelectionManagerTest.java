package fall2018.csc2017.coldwar;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PiecesSelectionManagerTest {
    PiecesSelectionManager manager;
    ArrayList<String> spyPostions;
    ArrayList<String> diplomatPositions;

    @Before
    public void setUp() {
        manager = new PiecesSelectionManager();
        spyPostions = new ArrayList<>();
        diplomatPositions = new ArrayList<>();
    }

    @Test
    public void testGetAgentPositionsValidInput() {
        setUp();
        Collections.addAll(spyPostions, "A2", "A3", "A4", "A5");
        Collections.addAll(diplomatPositions, "B1", "B2", "B3", "B4");
        assertNotNull(manager.getAgentPositions("p2", spyPostions, diplomatPositions));

        setUp();
        Collections.addAll(spyPostions, "F2", "F3", "F4", "F5");
        Collections.addAll(diplomatPositions, "E1", "E2", "E3", "E4");
        assertNotNull(manager.getAgentPositions("p1", spyPostions, diplomatPositions));
    }

    @Test
    public void testGetAgentPositionsInvalidInputSize() {
        setUp();
        Collections.addAll(spyPostions, "A2", "A2");
        Collections.addAll(diplomatPositions, "A3", "A4");
        assertNull(manager.getAgentPositions("p1", spyPostions, diplomatPositions));
    }

    @Test
    public void testGetAgentPositionsInvalidDuplicates() {
        setUp();
        Collections.addAll(spyPostions, "A2", "A2", "A3", "A4");
        Collections.addAll(diplomatPositions, "A5", "B1", "B2", "B3");
        assertNull(manager.getAgentPositions("p2", spyPostions, diplomatPositions));

        setUp();
        Collections.addAll(spyPostions, "F2", "F2", "F3", "F4");
        Collections.addAll(diplomatPositions, "E5", "E1", "E2", "E3");
        assertNull(manager.getAgentPositions("p1", spyPostions, diplomatPositions));
    }

    @Test
    public void testGetAgentPositionsInvalidIncorrectSideOfBoard() {
        setUp();
        Collections.addAll(spyPostions, "A2", "B4", "A3", "A4");
        Collections.addAll(diplomatPositions, "A5", "B1", "B2", "B3");
        assertNull(manager.getAgentPositions("p1", spyPostions, diplomatPositions));

        setUp();
        Collections.addAll(spyPostions, "F2", "F4", "F3", "F4");
        Collections.addAll(diplomatPositions, "E5", "E1", "E2", "E3");
        assertNull(manager.getAgentPositions("p2", spyPostions, diplomatPositions));
    }

    @Test
    public void testGetAgentPositionsInvalidPiecesInMiddleOfBoard() {
        setUp();
        Collections.addAll(spyPostions, "F2", "F4", "F3", "F4");
        Collections.addAll(diplomatPositions, "C1", "C2", "E2", "E3");
        assertNull(manager.getAgentPositions("p1", spyPostions, diplomatPositions));
    }

    @Test
    public void testGetAgentPositionsInvalidPositions() {
        setUp();
        Collections.addAll(spyPostions, "FF2", "F4", "F3", "F10");
        Collections.addAll(diplomatPositions, "E1", "E22", "L4", "E3");
        assertNull(manager.getAgentPositions("p1", spyPostions, diplomatPositions));
    }

    @Test
    public void testGetAgentPositionsInvalidOccupyBase() {
        setUp();
        Collections.addAll(spyPostions, "A1", "A2", "A3", "A4");
        Collections.addAll(diplomatPositions, "A5", "B1", "B2", "B3");
        assertNull(manager.getAgentPositions("p2", spyPostions, diplomatPositions));

        setUp();
        Collections.addAll(spyPostions, "F6", "F2", "F3", "F4");
        Collections.addAll(diplomatPositions, "E5", "E1", "E2", "E3");
        assertNull(manager.getAgentPositions("p1", spyPostions, diplomatPositions));
    }

}