package provenj;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.adobe.internal.xmp.XMPException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

// Creates an enclosure which is a temporary directory that contains all assets to be submitted
public class Enclosure {
    protected Path m_path;

    protected void init() throws IOException {
        m_path = Files.createTempDir().toPath();
        // TODO: change to Guava
        java.nio.file.Files.createDirectory(Paths.get(getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY)));
    }

    public Enclosure() throws IOException {
        init();
    }

    public String getPath(){
        return m_path.toString();
    }

    public String getPath(String element){
        return getPath() + System.getProperty("file.separator") + element;
    }

    public static String calculateFileHash(FileInputStream fileInputStream) throws NoSuchAlgorithmException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(32768);
        DigestOutputStream dos = new DigestOutputStream(baos, MessageDigest.getInstance("md5"));
        ByteStreams.copy(fileInputStream, dos);
        dos.close();
        return DatatypeConverter.printHexBinary(dos.getMessageDigest().digest());
    }

    public static String calculateFileHash(String path) throws NoSuchAlgorithmException, IOException {
        return calculateFileHash(new FileInputStream(path));
    }

    public Metadata fill(File file, Metadata metadata) throws IOException, XMPException, NoSuchAlgorithmException {
        return fill(file, metadata, false);
    }

    public Metadata fill(File file, Metadata metadata, boolean modifyOriginalFile) throws IOException, XMPException, NoSuchAlgorithmException {
        metadata = addContent(file, metadata, modifyOriginalFile);
        metadata = addManifest(metadata);
        metadata = addIndex(metadata);

        // has changed
        return metadata;
    }

    public Metadata addContent(File file, Metadata metadata, boolean modifyOriginalFile) throws IOException, XMPException, NoSuchAlgorithmException {
        // If filename is not provided we can infer it from the path sent in.
        if(Strings.isNullOrEmpty((metadata.getFileName())))
            metadata.setFileName(file.getName());

        // Create temporary output file
        File tempOutputFile = File.createTempFile("provenj", ".jpeg");
        FileOutputStream outputFileStream = new FileOutputStream(tempOutputFile.getCanonicalFile());

        // apply the metadata to the images
        ImageTagger imageTagger = new ImageTagger(metadata);
        FileInputStream inputFileStream = new FileInputStream(file);
        metadata.copy(imageTagger.tagAndClose(inputFileStream, outputFileStream));

        // copy the image file into the enclosure content directory
        Path finalOutputFilePath = Paths.get(getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY).toString(),
                                                     metadata.getFileName());

        if (modifyOriginalFile) {
            Files.copy(tempOutputFile, file);
        }
        Files.move(tempOutputFile, finalOutputFilePath.toFile());

        // calculate the image file hash and record it in the metadata
        metadata.setFileHashes(calculateFileHash(new FileInputStream( finalOutputFilePath.toFile())));
        return metadata;
    }

    public Metadata addManifest(Metadata metadata) throws IOException {
        // write the manifest to the enclosure
        ManifestCreator manifestCreator = new ManifestCreator(metadata);

        Path manifestFilePath = Paths.get(getPath(ProvenLib.PROVEN_MANIFEST));
        Files.write(manifestCreator.get().toJSONString(),
                    manifestFilePath.toFile(),
                    Charsets.UTF_8);
        return metadata.copy(manifestCreator);
    }

    public Metadata addIndex(Metadata metadata) throws IOException {
        // write the index to the enclosure
        Path indexFilePath = Paths.get(getPath(ProvenLib.PROVEN_INDEX));
        IndexCreator indexCreator = new IndexCreator(metadata);
        Files.write(indexCreator.toString(),
                    indexFilePath.toFile(),
                    Charsets.UTF_8);
        return metadata.copy(indexCreator);
    }

    public String publish() throws IOException {
        Distributor dist = new Distributor();
        return dist.publishIPFS(getPath());
    }
}
