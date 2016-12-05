package provenj;

import static j2html.TagCreator.*;

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
                                a().withHref(String.format("./%s/%s",
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
