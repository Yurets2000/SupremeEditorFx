package com.yube.custom.searcher;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class SearcherItemBaseFactory {

    private static final Map<String, SearcherItemBase> cache = new HashMap<>();

    public static  <E extends Enum<E>> SearcherItemBase getSearcherItemBase(E type) {
        if(!cache.containsKey(type.toString())) {
            String url = null;
            String color = null;
            if(type instanceof CodeSearcherTokenType) {
                CodeSearcherTokenType castedType = (CodeSearcherTokenType) type;
                switch (castedType) {
                    case TEXT:
                        url = "https://cdn3.iconfinder.com/data/icons/letters-and-numbers-1/32/letter_T_red-512.png";
                        color = "#d4998c";
                        break;
                    case WORD:
                        url = "https://cdn3.iconfinder.com/data/icons/letters-and-numbers-1/32/letter_W_red-512.png";
                        color = "#8ccad4";
                        break;
                    case REGEX:
                        url = "https://cdn3.iconfinder.com/data/icons/letters-and-numbers-1/32/letter_R_red-512.png";
                        color = "#e1a2fa";
                        break;
                }
            } else if (type instanceof CodeCompletorTokenType) {
                url = "https://cdn3.iconfinder.com/data/icons/letters-and-numbers-1/32/letter_C_red-512.png";
                color = "#5b9154";
            } else {
                throw new IllegalArgumentException("Can't get SearcherItemBase with such type");
            }
            Image icon = new Image(url, 16, 16, false, false);
            Background background = new Background(new BackgroundFill(Color.web(color), CornerRadii.EMPTY, Insets.EMPTY));
            cache.put(type.toString(), new SearcherItemBase(icon, background));
        }

        return cache.get(type.toString());
    }
}
