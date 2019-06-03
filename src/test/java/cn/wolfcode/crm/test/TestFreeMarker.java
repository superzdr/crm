package cn.wolfcode.crm.test;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Albert on 2019/6/1.
 */
public class TestFreeMarker {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDirectoryForTemplateLoading(new File("templates"));
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        Map root = new HashMap();
        // 在根中放入字符串"user"
        root.put("user", "Big Joe");

        Template temp = configuration.getTemplate("test.ftl");

        Writer out = new OutputStreamWriter(new FileOutputStream("test.html"));
        temp.process(root, out);
        out.flush();

    }
}
