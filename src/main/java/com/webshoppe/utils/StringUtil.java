package com.webshoppe.utils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;


@Component
public class StringUtil {

    public boolean isEmpty(String argument){
        return null == argument || "".equals(argument.trim());
    }

    public String getResourceAsString(String relativePathOfTheResource) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(relativePathOfTheResource).getFile());
        String content = new String(Files.readAllBytes(file.toPath()));

        return content;
    }

}
