package com.example.b07projectself;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Activity;
import android.widget.EditText;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    LoginActivityModel mockModel;

    @Mock
    LoginActivityView mockView;

    @Mock
    Activity mockActivity;

    @Mock
    EditText mockEditText;

    @Mock
    EditText mockEmailEditText;

    @Mock
    EditText mockPasswordEditText;

    LoginActivityPresenter presenter;

    @Before
    public void setUp() {
        presenter = new LoginActivityPresenter(mockView, mockModel);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testCheckInputNotEmpty() {
        // Set mock object length to 5 for testing
        when(mockEditText.length()).thenReturn(5);
        assertFalse(presenter.checkInput(mockEditText));
    }

    @Test
    public void testCheckInputEmpty() {
        // Set mock object length to 0 for testing
        when(mockEditText.length()).thenReturn(0);
        assertTrue(presenter.checkInput(mockEditText));
    }

    @Test
    public void testPresenterLoginSuccessful() {
        // Simulate successful login
        when(mockEmailEditText.length()).thenReturn(5);
        when(mockPasswordEditText.length()).thenReturn(5);

        // Call method under test
        presenter.presenterLogin(mockActivity, mockEmailEditText, mockPasswordEditText);

        // Verify that the model method is called
        verify(mockModel).modelLogin(eq(mockActivity), any(), eq(mockEmailEditText), eq(mockPasswordEditText));
    }

    @Test
    public void testPresenterLoginFailed() {
        // Calling method with null mock objects
        presenter.presenterLogin(mockActivity, mockEmailEditText, mockPasswordEditText);

        // Verify that the model method is not called
        verify(mockModel, never()).modelLogin(eq(mockActivity), any(), eq(mockEmailEditText), eq(mockPasswordEditText));
    }

    @Test
    public void testPresenterDisplayToast() {
        // Test Toast will be shown by LoginActivityView
        presenter.displayToast("Test!");
        verify(mockView).displayToast("Test!");
    }

    @Test
    public void testPresenterToApp(){
        User user = mock(User.class);
        presenter.toApp(user);
        verify(mockView).toApp(user);
    }
}