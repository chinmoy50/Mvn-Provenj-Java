package provenj;

import com.google.common.io.ByteStreams;
import com.adobe.internal.xmp.XMPException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

// Creates an enclosure which is a temporary directory that contains all assets to be submitted
public class Enclosure {
    protected Path m_path;

    protected void init() throws IOException {
        m_path = Files.createTempDirectory(ProvenLib.PROVEN_PREFIX);
        Files.createDirectory(getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY));
    }

    public Enclosure() throws IOException {
        init();
    }

    public Path getPath(){
        return m_path;
    }

    public Path getPath(String element) {
        return Paths.get(getPath().toString(), element);
    }

    protected static String calculateFileHash(Path path) throws NoSuchAlgorithmException, IOException {
        FileInputStream stream = new FileInputStream(path.toString());
        ByteArrayOutputStream baos = new ByteArrayOutputStream(32768);
        DigestOutputStream dos = new DigestOutputStream(baos, MessageDigest.getInstance("md5"));
        ByteStreams.copy(stream, dos);
        dos.close();
        return DatatypeConverter.printHexBinary(dos.getMessageDigest().digest());
    }

    public Metadata fillEnclosure(Path inputFilePath, Metadata metadata) throws IOException, XMPException, NoSuchAlgorithmException {
        metadata = addContent(inputFilePath, metadata);
        metadata = addManifest(metadata);
        metadata = addIndex(metadata);

        // has changed
        return metadata;
    }

    public Metadata addContent(Path inputFilePath, Metadata metadata) throws IOException, XMPException, NoSuchAlgorithmException {
        metadata.setFileName(inputFilePath.getFileName().toString());

        // Create temporary output file
        File tempOutputFile = File.createTempFile("provenj", ".jpeg");
        FileOutputStream outputFileStream = new FileOutputStream(tempOutputFile.getCanonicalFile());

        // apply the metadata to the images
        ImageTagger imageTagger = new ImageTagger(metadata);
        FileInputStream inputFileStream = new FileInputStream(inputFilePath.toFile());
        imageTagger.tagImage(inputFileStream, outputFileStream);
        inputFileStream.close();
        outputFileStream.close();

        // copy the image file into the enclosure content directory
        Path finalOutputFilePath = Paths.get(getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY).toString(),
                                                     metadata.getFileName());
        Files.move(tempOutputFile.toPath(),finalOutputFilePath);

        // calculate the image file hash and record it in the metadata
        metadata.setFileHashes(calculateFileHash(finalOutputFilePath));
        return metadata;
    }

    public Metadata addManifest(Metadata metadata) throws IOException {
        // write the manifest to the enclosure
        ManifestCreator manifestCreator = new ManifestCreator(metadata);
        Path manifestFilePath = getPath(ProvenLib.PROVEN_MANIFEST);
        Files.write(manifestFilePath,
                    manifestCreator.get().toJSONString().getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE);
        return metadata;
    }

    public Metadata addIndex(Metadata metadata) throws IOException {
        // write the index to the enclosure
        Path indexFilePath = getPath(ProvenLib.PROVEN_INDEX);
        IndexCreator indexCreator = new IndexCreator(metadata);
        Files.write(indexFilePath,
                    indexCreator.toString().getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE);
        return metadata;
    }
}
