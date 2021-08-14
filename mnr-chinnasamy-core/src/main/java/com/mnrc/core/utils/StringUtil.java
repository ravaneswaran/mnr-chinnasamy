package com.mnrc.core.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class StringUtil {

    public boolean isEmpty(String argument){
        return null == argument || "".equals(argument.trim());
    }

    public String getResourceAsString(String relativePathOfTheResource) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(this.getClass().getResource(relativePathOfTheResource).getFile());
        String content = new String(Files.readAllBytes(file.toPath()));

        return content;
    }

}
