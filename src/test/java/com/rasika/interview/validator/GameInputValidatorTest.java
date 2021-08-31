package com.rasika.interview.validator;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.rasika.interview.input.GameInput;
import com.rasika.interview.util.IOProcessor;
import com.rasika.interview.util.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.rasika.interview.helper.TestHelper.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class GameInputValidatorTest {

    @Mock
    private IOProcessor processor;
    private GameInput input;
    private InputValidator validator;

    @Before
    public void initialize() {
        input = new GameInput(RANGE, NUM_PLAYERS, ROWS, COLUMNS, NUMBERS_PER_ROW);
        validator = new GameInputValidator(input);
        Logger.setProcessor(processor);
    }

    @Test
    public void testInvalidRange() throws Exception {
        input.setRange(-5);

        doNothing().when(processor).writeLine(anyString());

        int status = SystemLambda.catchSystemExit(() -> {
            validator.validateGameInput();
        });
        assertEquals(1, status);
    }

    @Test
    public void testInvalidPlayers() throws Exception {
        input.setNumPlayers(1);

        doNothing().when(processor).writeLine(anyString());

        int status = SystemLambda.catchSystemExit(() -> {
            validator.validateGameInput();
        });
        assertEquals(1, status);
    }

    @Test
    public void testInvalidTicket() throws Exception {
        input.setColumns(3);

        doNothing().when(processor).writeLine(anyString());

        int status = SystemLambda.catchSystemExit(() -> {
            validator.validateGameInput();
        });
        assertEquals(1, status);
    }

    @Test
    public void testInvalidTicketTotalNumbers() throws Exception {
        input.setRows(2);
        input.setNumbersPerRow(2);

        doNothing().when(processor).writeLine(anyString());

        int status = SystemLambda.catchSystemExit(() -> {
            validator.validateGameInput();
        });
        assertEquals(1, status);
    }
}
