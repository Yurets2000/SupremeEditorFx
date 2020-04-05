package com.yube.configuration.transformers.sessions;

import com.yube.configuration.exceptions.TransformationException;
import com.yube.configuration.models.sessions.Session;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

public final class SessionTransformer {

    private SessionTransformer() {
    }

    public static Session createSession(Element element) throws TransformationException {
        if (!element.getName().equals("Session"))
            throw new TransformationException("Element name doesn't match 'Session'");
        try {
            Session session = new Session(
                    element.attributeValue("name"),
                    element.attributeValue("active"));
            session.setCreationTime(Long.valueOf(element.attributeValue("creationTime")));
            return session;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Element to Session object", ex);
        }
    }

    public static void updateElement(Element element, Session session) {
        if (!element.getName().equals("Session"))
            throw new TransformationException("Element name doesn't match 'Session'");
        element.addAttribute("active", session.getActive());
        element.addAttribute("creationTime", String.valueOf(session.getCreationTime()));
    }

    public static Element createElement(Session session) {
        try {
            Element element = new DOMElement("Session");
            element.addAttribute("name", session.getName());
            updateElement(element, session);
            Element filesElement = new DOMElement("Files");
            element.add(filesElement);
            return element;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Session to Element object", ex);
        }
    }
}
