package com.george.utils.generators.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.george.general.Constants;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * freemarker����
 */
public class FreemarkerDemo {

    private static final String TEMPLATE_PATH = "src/main/resources/templet/freemarker";
    private static final String CLASS_PATH = "src/main/java/com/george/";

    public static void main(String[] args) {
        /*// step1 ����freeMarker����ʵ��
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 ��ȡģ��·��
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 ��������ģ��
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("classPath", "com.george");
            dataMap.put("className", "AutoCodeDemo");
            dataMap.put("helloWorld", "ͨ���򵥵� <�����Զ���������> ��ʾ FreeMarker��HelloWorld��");
            // step4 ����ģ���ļ�
            Template template = configuration.getTemplate("hello.ftl");
            // step5 ��������
            File docFile = new File(CLASS_PATH + "/" + "AutoCodeDemo.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 ����ļ�
            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^AutoCodeDemo.java �ļ������ɹ� !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }*/

        FreemarkerUtil freemarkerUtil = FreemarkerUtil.getInstance(TEMPLATE_PATH);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("classPath", "com.george");
        model.put("className", "AutoCodeDemo");
        model.put("helloWorld", "ͨ���򵥵� <�����Զ���������> ��ʾ FreeMarker��HelloWorld��");
        freemarkerUtil.getContent(model, "hello.ftl");
//        freemarkerUtil.create(model, "hello.ftl", CLASS_PATH + "/AutoCodeDemo.java");
    }

}