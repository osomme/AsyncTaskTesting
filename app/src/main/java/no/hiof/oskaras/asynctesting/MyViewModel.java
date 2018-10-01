package no.hiof.oskaras.asynctesting;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<String> textContent = new MutableLiveData<>();

    public void setText(String text) {
        textContent.setValue(text);
    }

    public MutableLiveData<String> getText() {
        return textContent;
    }
}
