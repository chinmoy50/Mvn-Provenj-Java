package provenj;

import static j2html.TagCreator.*;

public class IndexCreator {

    static public String create(Manifest manifest) {
       String result = 
           html().with(
               head().with(
                   title("Proven")
               ),
               body().with(
                       main().with(
                               a().withHref(String.format("./payload/%s",manifest.getFileName())).with(
                                       p(manifest.getFileName())
                               ),
                               p(manifest.getEthereumBlockHash())
                       )
               )
           ).render();
       return result;
    }
}
