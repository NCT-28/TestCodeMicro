package com.source.base.file.utils;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@UtilityClass
public class IOUtils {
    public byte[] fileToByteArray(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }
}