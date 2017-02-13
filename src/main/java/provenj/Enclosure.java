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
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.ipfs.multibase.*;
import io.ipfs.multihash.*;

// Creates an enclosure which is a temporary directory that contains all assets to be submitted
public class Enclosure {
    protected String m_path;

    protected void init() throws IOException {
        m_path = Files.createTempDir().getPath();
        File file = new File(getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY));
        file.mkdir();
    }

    public Enclosure() throws IOException {
        init();
    }

    public String getPath(){
        return m_path;
    }

    public String getPath(String element){
        return getPath() + System.getProperty("file.separator") + element;
    }

    public static String calculateFileHashes(File file) throws NoSuchAlgorithmException, IOException {
        String result = "";
        Object[][] hashfuncs = new Object[][]{
                {Multihash.Type.sha1,     "SHA-1"},
                {Multihash.Type.sha2_256, "SHA-256"},
                {Multihash.Type.sha2_512, "SHA-512"}};

        for(Object[] hf: hashfuncs) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(32768);
            DigestOutputStream dos = new DigestOutputStream(baos, MessageDigest.getInstance((String)hf[1]));
            ByteStreams.copy(new FileInputStream(file), dos);
            dos.close();
            result += new Multihash((Multihash.Type)hf[0], dos.getMessageDigest().digest()).toString()
                   + " ";
        }
        return result.trim();
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
        File finalOutputFile = new File(getPath(ProvenLib.PROVEN_CONTENT_DIRECTORY),
                                                     metadata.getFileName());

        if (modifyOriginalFile) {
            Files.copy(tempOutputFile, file);
        }
        Files.move(tempOutputFile, finalOutputFile);

        // calculate the image file hash and record it in the metadata
        metadata.setFileHashes(calculateFileHashes(finalOutputFile));
        return metadata;
    }

    public Metadata addManifest(Metadata metadata) throws IOException {
        // write the manifest to the enclosure
        ManifestCreator manifestCreator = new ManifestCreator(metadata);

        File manifestFile = new File(getPath(ProvenLib.PROVEN_MANIFEST));
        Files.write(manifestCreator.get().toString(),
                    manifestFile,
                    Charsets.UTF_8);
        return metadata.copy(manifestCreator);
    }

    public Metadata addIndex(Metadata metadata) throws IOException {
        // write the index to the enclosure
        File indexFile = new File(getPath(ProvenLib.PROVEN_INDEX));
        IndexCreator indexCreator = new IndexCreator(metadata);
        Files.write(indexCreator.toString(),
                    indexFile,
                    Charsets.UTF_8);
        return metadata.copy(indexCreator);
    }

    public String publish() throws IOException {
        Distributor dist = new Distributor();
        return dist.publishIPFS(getPath());
    }
}
