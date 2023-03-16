# CMake project cleaner
This program purges all builder and other CMake files and
leaves only the C++ sources.

Example
```java
Cleaner cleaner = new CMakeCleaner(Paths.get("./projects"));
cleaner.addSaveExtension(".cpp");
cleaner.addSaveExtension(".h");
cleaner.clean();
```