package provenj;

import static j2html.TagCreator.*;

// Creates an index.html file to be included in the Proven enclosure.
public class IndexCreator extends Metadata {
    IndexCreator(Metadata metadata){
        super(metadata);
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
                                                           getFileName())).with(
                                        p(getFileName())
                                ),
                                p(getEthereumBlockHash())
                        )
                )
        ).render();
    }
}
