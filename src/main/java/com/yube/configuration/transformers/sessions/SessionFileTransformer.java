package com.yube.configuration.transformers.sessions;

import com.yube.configuration.exceptions.TransformationException;
import com.yube.configuration.models.sessions.SessionFile;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

public final class SessionFileTransformer {

    private SessionFileTransformer() {
    }

    public static SessionFile createSessionFile(Element element) {
        if (!element.getName().equals("File"))
            throw new TransformationException("Element name doesn't match 'File'");
        try {
            SessionFile sessionFile = new SessionFile(
                    element.attributeValue("id"),
                    element.attributeValue("name"),
                    element.attributeValue("encoding"));
            sessionFile.setLang(element.attributeValue("lang"));
            sessionFile.setFilePath(element.attributeValue("path"));
            sessionFile.setBackupFilePath(element.attributeValue("backupFilePath"));
            sessionFile.setDisplayIndex(Integer.parseInt(element.attributeValue("displayIndex")));
            return sessionFile;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed Element to SessionFile object", ex);
        }
    }

    public static void updateElement(Element element, SessionFile sessionFile){
        if (!element.getName().equals("File"))
            throw new TransformationException("Element name doesn't match 'File'");
        element.addAttribute("lang", sessionFile.getLang());
        element.addAttribute("path", sessionFile.getFilePath());
        element.addAttribute("backupFilePath", sessionFile.getBackupFilePath());
        element.addAttribute("encoding", sessionFile.getEncoding());
        element.addAttribute("displayIndex", String.valueOf(sessionFile.getDisplayIndex()));
    }

    public static Element createElement(SessionFile sessionFile) {
        try {
            Element element = new DOMElement("File");
            element.addAttribute("id", sessionFile.getId());
            element.addAttribute("name", sessionFile.getName());
            updateElement(element, sessionFile);
            return element;
        } catch (Exception ex) {
            throw new TransformationException("Can't transform passed SessionFile to Element object", ex);
        }
    }
}
