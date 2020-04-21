package com.yube.misc;

import com.yube.custom.AutocompleteItem;
import com.yube.custom.Link;
import javafx.scene.input.MouseEvent;

import java.util.List;

public abstract class Autocomplete<C, L extends Link> {

    protected abstract List<L> getLinks(C context);

    private AutocompleteItem createAutocompleteItemFromLink(L link){
        AutocompleteItem item = new AutocompleteItem(link.getUserRepresentation());
        item.addEventFilter(MouseEvent.MOUSE_CLICKED, link.getMouseHandler());
        return item;
    }
}
