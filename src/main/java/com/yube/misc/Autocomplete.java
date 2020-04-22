package com.yube.misc;

import com.yube.custom.*;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Autocomplete<T, E extends Enum<E>, SC extends SearchingContext<T, E>, L extends Link> {

    private AutocompleteBox createAutocompleteBox(){
        //AutocompleteHeader header = createAutocompleteHeader();
        //AutocompleteBody body = createAutocompleteBody();
        return null;
    }

    protected abstract List<L> getLinks(SC context);

    protected abstract AutocompleteHeader createAutocompleteHeader();

    private AutocompleteBody createAutocompleteBody(List<L> links){
        List<AutocompleteItem> autocompleteItems = links.stream()
                .map(this::createAutocompleteItemFromLink).collect(Collectors.toList());
        return new AutocompleteBody(autocompleteItems);
    }

    private AutocompleteItem createAutocompleteItemFromLink(L link){
        AutocompleteItem item = new AutocompleteItem(link.getUserRepresentation());
        item.addEventFilter(MouseEvent.MOUSE_CLICKED, link.getMouseHandler());
        return item;
    }
}
