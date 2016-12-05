package provenj;

import static j2html.TagCreator.*;

// Creates an index.html file to be included in the Proven enclosure.
public class IndexCreator {

    protected Manifest m_manifest;

    public IndexCreator(Manifest manifest){
        setManifest(manifest);
    }

    public void setManifest(Manifest manifest){
        m_manifest = manifest;
    }

    public Manifest getManifest()
    {
        return m_manifest;
    }

    public String toString() {
        return html().with(
                head().with(
                        title("Proven")
                ),
                body().with(
                        main().with(
                                a().withHref(String.format("./%1$s/%2$s",
                                                           ProvenLib.PROVEN_CONTENT_DIRECTORY,
                                                           getManifest().getFileName())).with(
                                        p(getManifest().getFileName())
                                ),
                                p(getManifest().getEthereumBlockHash())
                        )
                )
        ).render();
    }
}
