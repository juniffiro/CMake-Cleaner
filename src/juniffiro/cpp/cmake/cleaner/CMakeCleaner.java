package juniffiro.cpp.cmake.cleaner;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * | ( Created ) ( by ) ( @juniffiro ) |
 * | 16/03/2023                        |
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */

public class CMakeCleaner extends Cleaner {

    private FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            if (path.getFileName().toString().contains("CMake")
                    || notEnds(path)) {
                System.out.println("Delete file: " + path);
                Files.delete(path);
            } else {
                System.out.println("File skipped: " + path);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            //   Files.delete(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            if (exc != null) {
                throw exc;
            }
            System.out.println("\n------ Processing new dir ------\n");
            File dirFile = dir.toFile();
            if (dirFile.list().length == 0) {
                Files.delete(dir);
            } else {
                System.out.println("The directory contains the necessary files");
            }
            return FileVisitResult.CONTINUE;
        }
    };

    public CMakeCleaner(Path rootPath) {
        super(rootPath);
    }

    public CMakeCleaner(Path rootPath, Set<String> filesExtensions) {
        super(rootPath, filesExtensions);
    }

    @Override
    public FileVisitor<Path> visitor() {
        return visitor;
    }
}
