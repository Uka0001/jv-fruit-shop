package core.basesyntax.service.impl;

import core.basesyntax.service.ReaderService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReaderServiceImpl implements ReaderService {

    @Override
    public String readFromFile(String path) {
        String lines;
        try {
            lines = Files.readString(Path.of(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file " + path, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + path, e);
        }
        return lines;
    }
}
