package provenj;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Publisher {
    // top seekrit
    static private String ebg(String s){
        String r=""; int n = 015;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'm')
             || (c >= 'A' && c <= 'M')) c += n;
            else if((c >= 'n' && c <= 'z')
                 || (c >= 'N' && c <= 'Z')) c -= n;
            r += c;
        }
        return r;
    }
    static public String publishToGateway(String ipfsHash){
        String result = "";
        OkHttpClient client = new OkHttpClient();
        // We're using a gateway because we don't have a local Ethereum node
        // running. Hide the gateway name from spambots reading the code.
        String url = ebg(new StringBuilder("fabvgvfbcrqSaribecSzbpNkhavyrzbuNlsveriSSTcggu".replace("S","/").replace("N",".").replace("T",":")).reverse().toString());
        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON,  "{\"ipfsHash\": \"" + ipfsHash + "\"}");
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        }
        catch(IOException e){
        }
        return result;
    }
}
