package zany.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileUtils {

    public static byte[] readSmallFileBytes(String name) {
        byte[] filearray = null;
        
        try {
            Path path = FileSystems.getDefault().getPath(".", name);
            return Files.readAllBytes(path);
            
        } catch ( IOException ioe ) {
            ioe.printStackTrace();
        }
        
        return filearray;
    }

    public static List<String> readSmallFileLines(String name) {
        try {
            return Files.readAllLines( FileSystems.getDefault().getPath(".", name), 
                                       Charset.defaultCharset() 
                                       );
            
        } catch ( IOException ioe ) {
            ioe.printStackTrace();
        }
        
        return null;
    }

    public static List<String> readLargeFileLines(String name) {
        try {
            BufferedReader reader = Files.newBufferedReader( FileSystems.getDefault().getPath(".", name), 
                                                             Charset.defaultCharset() 
                                                             );

            List<String> lines = new ArrayList<>();
            String line = null;
            while ( (line = reader.readLine()) != null ){
                lines.add(line);
            }

            return lines;
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } 
        
        return null;
    }

    public List<String> readLargeFileLinesMixed(String name) {
        try {
            Path path             = FileSystems.getDefault().getPath(".", name);
            InputStream in        = Files.newInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            List<String> lines = new ArrayList<>();
            String line = null;
            while ( (line = reader.readLine()) != null ){
                lines.add(line);
            }

            return lines;
            
        } catch ( IOException ioe ) {
            ioe.printStackTrace();
        }

        return null;
    }

    public static void writeFileBytes(String filename, String content) {
        try {
            Files.write( FileSystems.getDefault().getPath(".", filename), 
                         content.getBytes(), 
                         StandardOpenOption.CREATE);
            
        } catch ( IOException ioe ) {
            ioe.printStackTrace();
        }
        
    }

    public static void writeFileBytesBuffered(String filename, String content) {
        try {
            BufferedWriter writer = Files.newBufferedWriter( FileSystems.getDefault().getPath(".", filename),
                                                             Charset.forName("UTF-8"), StandardOpenOption.CREATE
                                                             );
            writer.write(content, 0, content.length());
        }
        catch ( IOException ioe ) {
            ioe.printStackTrace();
        }
    }

    public static List<Path> getDirectories(final String dir) throws IOException {
        final List<Path> dirlist = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(FileSystems.getDefault().getPath(dir))) {
            for (final Iterator<Path> it = stream.iterator(); it.hasNext();) {
                dirlist.add(FileSystems.getDefault().getPath(dir).resolve(it.next()));
            }
        }
        return dirlist;
    }

    public static List<Path> getEntries(final String dir) throws IOException {
        final List<Path> entries = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(FileSystems.getDefault().getPath(dir))) {
            for (final Iterator<Path> it = stream.iterator(); it.hasNext();) {
                entries.add(it.next());
            }
        }
        return entries;
    }

    public static List<Path> getEntries(final String dir, final DirectoryStream.Filter<? super Path> filter) throws IOException {
        final List<Path> entries = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(FileSystems.getDefault().getPath(dir), filter)) {
            for (final Iterator<Path> it = stream.iterator(); it.hasNext();) {
                entries.add(it.next());
            }
        }
        return entries;
    }

    public static List<Path> getEntries(final String dir, final String regularExpression) throws IOException {
        final List<Path> entries = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(FileSystems.getDefault().getPath(dir), regularExpression)) {
            for (final Iterator<Path> it = stream.iterator(); it.hasNext();) {
                entries.add(it.next());
            }
        }
        return entries;
    }


}
