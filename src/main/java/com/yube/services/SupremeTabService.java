package com.yube.services;

import com.yube.configuration.models.sessions.SessionFile;
import com.yube.custom.SupremeArea;
import com.yube.custom.SupremeTab;
import com.yube.main.StageContainer;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class SupremeTabService {

    private SupremeAreaService supremeAreaService;

    @Autowired
    public SupremeTabService(SupremeAreaService supremeAreaService){
        this.supremeAreaService = supremeAreaService;
    }

    public SupremeTab createTab(StageContainer container){
        SupremeTab tab = new SupremeTab();
        createTabContent(tab, container);
        return tab;
    }

    public SupremeTab createTab(SessionFile sessionFile, StageContainer container){
        SupremeTab tab = new SupremeTab();
        tab.setFileId(sessionFile.getId());
        tab.setText(sessionFile.getName());
        createTabContent(tab, container);
        return tab;
    }

    private void createTabContent(SupremeTab tab, StageContainer container){
        String test = "package beans;\n" +
                "\n" +
                "import dao.CourseDao;\n" +
                "import dto.Course;\n" +
                "import org.hibernate.Session;\n" +
                "import utils.HibernateUtil;\n" +
                "\n" +
                "import javax.faces.bean.ManagedBean;\n" +
                "import javax.faces.bean.SessionScoped;\n" +
                "import java.util.LinkedHashMap;\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "import java.util.stream.Collectors;\n" +
                "\n" +
                "@ManagedBean\n" +
                "@SessionScoped\n" +
                "public class CourseData {\n" +
                "\n" +
                "    private TableElement<Course> courseBean = new TableElement<>(new Course());\n" +
                "    private List<TableElement<Course>> courseBeans;\n" +
                "    private Map<String, Course> coursesMap;\n" +
                "\n" +
                "    public CourseData() {\n" +
                "        refreshCourseBeans();\n" +
                "    }\n" +
                "\n" +
                "    public TableElement<Course> getCourseBean() {\n" +
                "        return courseBean;\n" +
                "    }\n" +
                "\n" +
                "    public void setCourseBean(TableElement<Course> courseBean) {\n" +
                "        this.courseBean = courseBean;\n" +
                "    }\n" +
                "\n" +
                "    public List<TableElement<Course>> getCourseBeans() {\n" +
                "        return courseBeans;\n" +
                "    }\n" +
                "\n" +
                "    public Map<String, Course> getCoursesMap() {\n" +
                "        return coursesMap;\n" +
                "    }\n" +
                "\n" +
                "    public void addCourse() {\n" +
                "        courseBeans.add(courseBean);\n" +
                "        Course element = courseBean.getElement();\n" +
                "        coursesMap.put(element.toString(), element);\n" +
                "\n" +
                "        Session session = HibernateUtil.getSessionFactory().openSession();\n" +
                "        CourseDao courseDao = new CourseDao(session);\n" +
                "        courseDao.save(courseBean.getElement());\n" +
                "        session.close();\n" +
                "        courseBean = new TableElement<>(new Course());\n" +
                "    }\n" +
                "\n" +
                "    public void editCourse(TableElement<Course> courseBean) {\n" +
                "        coursesMap.remove(courseBean.getElement().toString());\n" +
                "        courseBean.setEditable(true);\n" +
                "    }\n" +
                "\n" +
                "    public void updateCourse(TableElement<Course> courseBean) {\n" +
                "        Course element = courseBean.getElement();\n" +
                "        coursesMap.put(element.toString(), element);\n" +
                "\n" +
                "        courseBean.setEditable(false);\n" +
                "        Session session = HibernateUtil.getSessionFactory().openSession();\n" +
                "        CourseDao courseDao = new CourseDao(session);\n" +
                "        courseDao.update(courseBean.getElement());\n" +
                "        session.close();\n" +
                "    }\n" +
                "\n" +
                "    public void deleteCourse(TableElement<Course> courseBean) {\n" +
                "        courseBeans.remove(courseBean);\n" +
                "        coursesMap.remove(courseBean.getElement().toString());\n" +
                "\n" +
                "        Session session = HibernateUtil.getSessionFactory().openSession();\n" +
                "        CourseDao courseDao = new CourseDao(session);\n" +
                "        courseDao.delete(courseBean.getElement());\n" +
                "        session.close();\n" +
                "    }\n" +
                "\n" +
                "    public String redirect() {\n" +
                "        refreshCourseBeans();\n" +
                "        return \"courses.xhtml\";\n" +
                "    }\n" +
                "\n" +
                "    public void refreshCourseBeans() {\n" +
                "        Session session = HibernateUtil.getSessionFactory().openSession();\n" +
                "        CourseDao courseDao = new CourseDao(session);\n" +
                "        List<Course> courses = courseDao.getAll();\n" +
                "        courseBeans = courses.stream().map(TableElement<Course>::new).collect(Collectors.toList());\n" +
                "        session.close();\n" +
                "        refreshCoursesMap();\n" +
                "    }\n" +
                "\n" +
                "    private void refreshCoursesMap() {\n" +
                "        coursesMap = new LinkedHashMap<>();\n" +
                "        courseBeans.stream().map(TableElement::getElement)\n" +
                "                .forEach(group -> coursesMap.put(group.toString(), group));\n" +
                "    }\n" +
                "}\n";
        SupremeArea area = supremeAreaService.createArea(test, container);
        area.setPrefHeight(2000);
        VirtualizedScrollPane<SupremeArea> scrollPane = new VirtualizedScrollPane<>(area);
        tab.setContent(scrollPane);
    }
}
