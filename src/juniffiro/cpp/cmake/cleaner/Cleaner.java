package juniffiro.cpp.cmake.cleaner;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * | ( Created ) ( by ) ( @juniffiro ) |
 * | 16/03/2023                        |
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */

public abstract class Cleaner {

    // Extension of files that will not be deleted
    private final Set<String> filesExtensions;

    // Project file directory
    private final Path rootPath;

    public Cleaner(Path rootPath) {
        this(rootPath, new HashSet<>());
    }

    public Cleaner(Path rootPath, Set<String> filesExtensions) {
        this.filesExtensions = filesExtensions;
        this.rootPath = rootPath;
    }

    /**
     * Add file to exclusion list.
     *
     * @param extension
     *        File extension.
     */
    public void addSaveExtension(String extension) {
        filesExtensions.add(extension);
    }

    /**
     * Start cleaning process.
     *
     * @throws IOException
     *         In case of process errors
     */
    public void clean() throws IOException {
        deleteDirectoryWalkTree(rootPath);
    }

    public Path getRootPath() {
        return rootPath;
    }

    public Set<String> getFilesExtensions() {
        return filesExtensions;
    }

    protected boolean notEnds(Path path) {
        String ends = path.getFileName().toString();
        return filesExtensions.stream().noneMatch(ends::endsWith);
    }

    /**
     * Deletion process handler.
     */
    public abstract FileVisitor<Path> visitor();

    private void deleteDirectoryWalkTree(Path p) throws IOException {
        Files.walkFileTree(p, visitor());
    }
}
