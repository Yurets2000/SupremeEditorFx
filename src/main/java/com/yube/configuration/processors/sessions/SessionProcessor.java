package com.yube.configuration.processors.sessions;

import com.yube.configuration.models.sessions.Session;
import com.yube.configuration.processors.AbstractProcessor;
import com.yube.configuration.transformers.sessions.SessionTransformer;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class SessionProcessor extends AbstractProcessor {

    public SessionProcessor(Document document) {
        super(document);
    }

    public Session getSession(String name) {
        Element sessionElement = (Element) document.selectSingleNode("//Session[@name='" + name + "']");
        if (sessionElement == null) throw new IllegalArgumentException("Can't get such Session");
        return SessionTransformer.createSession(sessionElement);
    }

    public Session getActiveSession() {
        Attribute attribute = (Attribute) document.selectObject("//Sessions/@active");
        String active = attribute.getValue();
        if (active == null) return null;
        return getSession(active);
    }

    public void setActiveSession(String name) {
        Element sessionsElement = (Element) document.selectSingleNode("//Sessions");
        sessionsElement.attribute("active").setValue(name);
    }

    public List<Session> getSessions() {
        List nodes = document.selectNodes("//Session/@name");
        List<Session> sessions= new ArrayList<>();
        for (Object o : nodes) {
            Attribute attribute = (Attribute) o;
            sessions.add(getSession(attribute.getValue()));
        }
        return sessions;
    }

    public void addSession(Session session) {
        Element sessionsElement = (Element) document.selectSingleNode("//Sessions");
        Element sessionElement = SessionTransformer.createElement(session);
        sessionsElement.add(sessionElement);
    }

    public void updateSessionAttributes(Session session) {
        Element sessionElement = (Element) document.selectSingleNode("//Session[@name='" + session.getName() + "']");
        if (sessionElement == null) throw new IllegalArgumentException("Can't get such Session");
        SessionTransformer.updateElement(sessionElement, session);
    }

    public void deleteSession(String name) {
        Element sessionsElement = (Element) document.selectSingleNode("//Sessions");
        Element sessionElement = (Element) document.selectSingleNode("//Session[@name='" + name + "']");
        if (sessionElement == null) throw new IllegalArgumentException("Can't get such Session");
        sessionsElement.remove(sessionElement);
    }
}
