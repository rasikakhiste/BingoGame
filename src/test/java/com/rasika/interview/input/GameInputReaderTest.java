package com.rasika.interview.input;

import com.rasika.interview.util.IOProcessor;
import com.rasika.interview.util.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static com.rasika.interview.helper.TestHelper.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameInputReaderTest {

    private static final String VALUE = "1";

    private InputReader inputReader;

    @Mock
    private IOProcessor processor;

    @Before
    public void initialize() {
        inputReader = new GameInputReader(processor);
        Logger.setProcessor(processor);
    }

    @Test
    public void testReadGameInput() throws IOException {
        when(processor.readNextLine()).thenReturn(VALUE);
        GameInput input = inputReader.readGameInput();

        assertEquals(Integer.parseInt(VALUE), input.getRange());
        assertEquals(Integer.parseInt(VALUE), input.getRows());
        assertEquals(Integer.parseInt(VALUE), input.getColumns());
        assertEquals(Integer.parseInt(VALUE), input.getNumPlayers());
        assertEquals(Integer.parseInt(VALUE), input.getNumbersPerRow());
    }

    @Test
    public void testReadGameInputWithException() throws IOException {
        when(processor.readNextLine()).thenThrow(new IOException());
        GameInput input = inputReader.readGameInput();

        assertEquals(-1, input.getRange());
        assertEquals(-1, input.getRows());
        assertEquals(-1, input.getColumns());
        assertEquals(-1, input.getNumPlayers());
        assertEquals(-1, input.getNumbersPerRow());
    }

    @Test
    public void testReadGameInputWithDefaultValues() throws IOException {
        when(processor.readNextLine()).thenReturn(null);
        GameInput input = inputReader.readGameInput();

        assertEquals(99, input.getRange());
        assertEquals(ROWS, input.getRows());
        assertEquals(COLUMNS, input.getColumns());
        assertEquals(2, input.getNumPlayers());
        assertEquals(NUMBERS_PER_ROW, input.getNumbersPerRow());
    }
}
