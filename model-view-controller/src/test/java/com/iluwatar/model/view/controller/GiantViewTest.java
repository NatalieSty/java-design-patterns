package com.iluwatar.model.view.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Date: 12/20/15 - 2:04 PM
 *
 * @author Jeroen Meulemeester
 */
public class GiantViewTest {

  /**
   * The mocked standard out {@link PrintStream}, required since the actions of the views don't have
   * any influence on any other accessible objects, except for writing to std-out using {@link
   * System#out}
   */
  private final PrintStream stdOutMock = mock(PrintStream.class);

  /**
   * Keep the original std-out so it can be restored after the test
   */
  private final PrintStream stdOutOrig = System.out;

  /**
   * Inject the mocked std-out {@link PrintStream} into the {@link System} class before each test
   */
  @Before
  public void setUp() {
    System.setOut(this.stdOutMock);
  }

  /**
   * Removed the mocked std-out {@link PrintStream} again from the {@link System} class
   */
  @After
  public void tearDown() {
    System.setOut(this.stdOutOrig);
  }

  /**
   * Verify if the {@link GiantView} does what it has to do: Print the {@link GiantModel} to the
   * standard out stream, nothing more, nothing less.
   */
  @Test
  public void testDisplayGiant() {
    final GiantView view = new GiantView();

    final GiantModel model = mock(GiantModel.class);
    view.displayGiant(model);

    verify(this.stdOutMock).println(model);
    verifyNoMoreInteractions(model, this.stdOutMock);

  }

}