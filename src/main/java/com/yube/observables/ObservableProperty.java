package com.yube.observables;

import java.util.Observable;

public class ObservableProperty<T> extends Observable {

    private T property;

    public ObservableProperty(T property){
        this.property = property;
    }

    public T getProperty() {
        return property;
    }

    public void setProperty(T property) {
        this.property = property;
        setChanged();
        notifyObservers();
    }
}
