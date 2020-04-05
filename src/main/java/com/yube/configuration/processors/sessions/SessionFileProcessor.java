package com.yube.configuration.processors.sessions;

import com.yube.configuration.models.sessions.SessionFile;
import com.yube.configuration.processors.AbstractProcessor;
import com.yube.configuration.transformers.sessions.SessionFileTransformer;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class SessionFileProcessor extends AbstractProcessor {

    public SessionFileProcessor(Document document) {
        super(document);
    }

    public SessionFile getSessionFile(String id) {
        Element fileElement = (Element) document.selectSingleNode("//File[@id='" + id + "']");
        if (fileElement == null) throw new IllegalArgumentException("Can't get such File");
        return SessionFileTransformer.createSessionFile(fileElement);
    }

    public SessionFile getActiveFile(String sessionName) {
        Attribute attribute = (Attribute) document.selectSingleNode("//Session[@name='" + sessionName + "']/@active");
        if (attribute == null) throw new IllegalArgumentException("Can't get such Session");
        String active = attribute.getValue();
        if (active == null) return null;
        return getSessionFile(active);
    }

    public void setActiveFile(String sessionName, String id) {
        Element sessionElement = (Element) document.selectSingleNode("//Session[@name='" + sessionName + "']");
        if (sessionElement == null) throw new IllegalArgumentException("Can't get such Session");
        sessionElement.attribute("active").setValue(id);
    }

    public List<SessionFile> getSessionFiles(String sessionName) {
        List nodes = document.selectNodes("//Session[@name='" + sessionName + "']/Files//File/@id");
        if (nodes == null) throw new IllegalArgumentException("Can't get Files from such Session");
        List<SessionFile> sessionFiles = new ArrayList<>();
        for (Object o : nodes) {
            Attribute attribute = (Attribute) o;
            sessionFiles.add(getSessionFile(attribute.getValue()));
        }
        return sessionFiles;
    }

    public void addFile(String sessionName, SessionFile sessionFile) {
        Element filesElement = (Element) document.selectSingleNode("//Session[@name='" + sessionName + "']/Files");
        if (filesElement == null) throw new IllegalArgumentException("Can't get such Session");
        Element fileElement = SessionFileTransformer.createElement(sessionFile);
        filesElement.add(fileElement);
    }

    public void updateFile(SessionFile sessionFile) {
        Element fileElement = (Element) document.selectSingleNode("//File[@id='" + sessionFile.getId() + "']");
        if (fileElement == null) throw new IllegalArgumentException("Can't get such File");
        SessionFileTransformer.updateElement(fileElement, sessionFile);
    }

    public void deleteFile(String sessionName, String id) {
        Element filesElement = (Element) document.selectSingleNode("//Session[@name='" + sessionName + "']/Files");
        if (filesElement == null) throw new IllegalArgumentException("Can't get such Session");
        Element fileElement = (Element) document.selectSingleNode("//File[@id='" + id + "']");
        if (fileElement == null) throw new IllegalArgumentException("Can't get such File");
        filesElement.remove(fileElement);
    }
}
