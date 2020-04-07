package com.yube.configuration.processors.actions;

import com.yube.configuration.models.actions.Action;
import com.yube.configuration.models.actions.Implementor;
import com.yube.configuration.processors.AbstractProcessor;
import com.yube.configuration.transformers.actions.ActionTransformer;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActionsProcessor extends AbstractProcessor {

    public ActionsProcessor(Document document) {
        super(document);
    }

    public Action getAction(String name) {
        Element actionElement = (Element) document.selectSingleNode("//Action[@name='" + name + "']");
        if (actionElement == null) throw new IllegalArgumentException("Can't find such action");
        return ActionTransformer.createAction(actionElement);
    }

    public List<Action> getSupportedActions() {
        List nodes = document.selectNodes("//Action/@name");
        List<Action> actions = new ArrayList<>();
        for (Object o : nodes) {
            Attribute attribute = (Attribute) o;
            actions.add(getAction(attribute.getValue()));
        }
        return actions;
    }

    public List<Action> getDefinedActions(String qualifier) {
        return getSupportedActions().stream()
                .filter(a -> a.getDefiners().stream().anyMatch(d -> d.getValue().equals(qualifier)))
                .collect(Collectors.toList());
    }

    public List<Action> getImplementedActions(String type, String qualifier) {
        return getSupportedActions().stream().filter(a -> isImplementor(a, type, qualifier))
                .collect(Collectors.toList());
    }

    public boolean isImplementor(Action action, String type, String qualifier) {
        Optional<Implementor> optionalImplementor = action.getImplementors().stream().filter(impl -> impl.getType().equals(type)).findAny();
        if (optionalImplementor.isPresent()) {
            Implementor implementor = optionalImplementor.get();
            implementor.isRelatedTo(type, qualifier);
        }
        return false;
    }
}
