package provenj;

// Retrieves info on the Bitcoin blockchain
public class BitcoinInfo extends BlockchainInfo {
    protected int getInterval(){
        // In theory there is a new Bitcoin block every 10 minutes
        return 10*60;
    }

    protected void fetchLatest(){
        // "https://blockchain.info/latestblock"
    }
}
