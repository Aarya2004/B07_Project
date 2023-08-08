package com.example.b07projectself;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    LoginActivityView view;

    @Mock
    LoginActivityModel model;

    @Mock
    Editable emailEdit;

    @Mock
    Editable passEdit;

    @Mock
    Editable nameEdit;

    @Mock
    DatabaseReference db;
    @Mock
    PopupWindow pop;

    @Mock
    EditText name;

    @Test
    public void testPresenterEmptyEmail(){
        LoginActivityPresenter presenter = new LoginActivityPresenter(view, model);
        when(view.emailAddress.getText()).thenReturn(emailEdit);
        when(emailEdit.toString()).thenReturn("");
        assertTrue(presenter.checkInput(view.emailAddress));

    }
    @Test
    public void testPresenterEmptyPass(){
        LoginActivityPresenter presenter = new LoginActivityPresenter(view, model);
        when(view.password.getText()).thenReturn(passEdit);
        when(passEdit.toString()).thenReturn("");
        assertTrue(presenter.checkInput(view.password));

    }

//    @Test
//    public void testPresenterUserDNE(){
//        LoginActivityPresenter presenter = new LoginActivityPresenter(view, model);
//        when(view.emailAddress.getText()).thenReturn(emailEdit);
//        when(emailEdit.toString()).thenReturn("ownerdne@gmail.com");
//        when(view.password.getText()).thenReturn(passEdit);
//        when(passEdit.toString()).thenReturn("ownerdne");
//        presenter.presenterLogin(view, view.emailAddress, view.password);
//        verify(view).displayToast("Authentication failed.");
//    }
//
//    @Test
//    public void testPresenterOwner(){
//        LoginActivityPresenter presenter = new LoginActivityPresenter(view, model);
//        when(view.emailAddress.getText()).thenReturn(emailEdit);
//        when(emailEdit.toString()).thenReturn("ownertest@gmail.com");
//        when(view.password.getText()).thenReturn(passEdit);
//        when(passEdit.toString()).thenReturn("ownertest");
//        when(name.getText()).thenReturn(nameEdit);
//        when(nameEdit.toString()).thenReturn("storetest");
//        presenter.presenterRegisterOwner(view, db, view.emailAddress, view.password, name, pop);
//
//    }


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}