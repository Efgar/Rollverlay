package com.efgh.avraelayout.ui.css;

import com.efgh.avraelayout.persistence.ConfigGateway;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Themes {
    CLASSIC,
    SOBER;

    Logger logger = Logger.getRootLogger();

    public List<String> getCssList() {
        List<String> cssList = new ArrayList<>();
        try {
            for (String fileName : getResourceListing("styles/themes/")) {
                if (fileName.endsWith(name().toLowerCase() + ".css")) {
                    cssList.add(getClass().getClassLoader().getResource(fileName).toURI().toString());
                }
            }

            for (String fileName : getResourceListing("styles/general/")) {
                cssList.add(getClass().getClassLoader().getResource(fileName).toURI().toString());
            }
        } catch (Exception e) {
            logger.error("Can not load CSS files into the system", e);
        }
        return cssList;
    }

    public static Themes getConfiguredTheme() {
        return getTheme(ConfigGateway.getConfiguredTheme());
    }

    private Set<String> getResourceListing(String path) throws URISyntaxException, IOException {
        URL dirURL = getClass().getClassLoader().getResource(path);
        if (dirURL != null && dirURL.getProtocol().equals("file")) {
            return Stream.of(ObjectUtils.defaultIfNull(new File(dirURL.toURI()).list(), new String[]{})).map(filePath -> path + filePath).collect(Collectors.toSet());
        }

        if (dirURL == null) {
            String me = getClass().getName().replace(".", "/") + ".class";
            dirURL = getClass().getClassLoader().getResource(me);
        }

        if (dirURL != null && dirURL.getProtocol().equals("jar")) {
            String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!"));
            JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
            Enumeration<JarEntry> entries = jar.entries();
            Set<String> result = new HashSet<>();
            while (entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (name.startsWith(path)) {
                    result.add(name);
                }
            }
            return result;
        }
        throw new UnsupportedOperationException("Cannot list files for URL " + dirURL);
    }

    private static Themes getTheme(String themeName) {
        for (Themes theme : Themes.values()) {
            if (theme.name().equalsIgnoreCase(themeName)) {
                return theme;
            }
        }
        return CLASSIC;
    }
}
