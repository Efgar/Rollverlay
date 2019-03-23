package com.efgh.avraelayout.ui.css;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Themes {
    TRADITIONAL("traditional");

    Logger log = Logger.getAnonymousLogger();
    String folder;

    Themes(String folder) {
        this.folder = folder;
    }

    public List<String> getCssList() {
        List<String> cssList = new ArrayList<>();
        try {
            Path cssFolderPath = Paths.get(getClass().getResource("/css/" + folder).toURI());
            Stream<Path> cssFiles = Files.list(cssFolderPath);
            for (Path cssFile : cssFiles.collect(Collectors.toList())){
                cssList.add(cssFile.toUri().toURL().toExternalForm());
            }
        } catch (Exception e) {
            log.warning("Can not load CSS files into the system");
        }
        return cssList;
    }
}
