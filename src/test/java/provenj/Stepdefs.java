package provenj;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;
import org.json.simple.JSONObject;
import java.util.UUID;
import static org.junit.Assert.assertEquals;

public class Stepdefs {

    // Build manifest
    Manifest manifest = new Manifest();

    @Given("^a JPEG file named \"([^\"]*)\"$")
    public void a_JPEG_file_named(String filename) throws Throwable {
	manifest.addFile(filename);
    }

    @Given("^the current Bitcoin block number (\\d+)$")
    public void the_current_Bitcoin_block_number(int blockNumber) throws Throwable {
	manifest.setBitcoinBlockNumber(blockNumber);
    }

    @Given("^the current Bitcoin block hash \"([^\"]*)\"$")
    public void the_current_Bitcoin_block_hash(String blockHash) throws Throwable {
	manifest.setBitcoinBlockHash(blockHash);
    }

    @Given("^the current Ethereum block number (\\d+)$")
    public void the_current_Ethereum_block_number(int blockNumber) throws Throwable {
	manifest.setEthereumBlockNumber(blockNumber);
    }

    @Given("^the current Ethereum block hash \"([^\"]*)\"$")
    public void the_current_Ethereum_block_hash(String blockHash) throws Throwable {
	manifest.setEthereumBlockHash(blockHash);
    }

    @Given("^the IPFS hash from the last submitted file \"([^\"]*)\"$")
    public void the_IPFS_hash_from_the_last_submitted_file(String ipfsHash) throws Throwable {
	manifest.setPreviousIPFSHash(ipfsHash);
    }

    @Given("^the other hashes from the last submitted file \"([^\"]*)\"$")
    public void the_other_hashes_from_the_last_submitted_file(String fileHashes) throws Throwable {
	manifest.setPreviousFileHashes(fileHashes);
    }

    @Given("^the GUID for the submission \"([^\"]*)\"$")
    public void the_GUID_for_the_submission(String guid) throws Throwable {
	manifest.setGUID(UUID.fromString(guid));
    }

    @When("^I ask for a manifest file$")
    public void i_ask_for_a_manifest_file() throws Throwable {
        JSONObject json = manifest.get();
    }

    @Then("^manifest\\.FileName should equal \"([^\"]*)\"$")
    public void manifest_FileName_should_equal(String filename) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((String)json.get("FileName"),filename);
    }

    @Then("^manifest\\.BitcoinBlockNumber should be (\\d+)$")
    public void manifest_BitcoinBlockNumber_should_be(int bitcoinBlockNumber) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((int)json.get("BitcoinBlockNumber"),bitcoinBlockNumber);
    }

    @Then("^manifest\\.BitcoinBlockHash should equal \"([^\"]*)\"$")
    public void manifest_BitcoinBlockHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((String)json.get("BitcoinBlockHash"),hash);
    }

    @Then("^manifest\\.EthereumBlockNumber should equal (\\d+)$")
    public void manifest_EthereumBlockNumber_should_equal(int num) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((int)json.get("EthereumBlockNumber"),num);
    }

    @Then("^manifest\\.EthereumBlockHash should equal \"([^\"]*)\"$")
    public void manifest_EthereumBlockHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((String)json.get("EthereumBlockHash"),hash);
    }

    @Then("^manifest\\.PreviousIFPSHash should equal \"([^\"]*)\"$")
    public void manifest_PreviousIFPSHash_should_equal(String hash) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((String)json.get("PreviousIPFSHash"),hash);
    }

    @Then("^manifest\\.PreviousFileHashes should equal \"([^\"]*)\"$")
    public void manifest_PreviousFileHashes_should_equal(String hashes) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((String)json.get("PreviousFileHashes"),hashes);
    }

    @Then("^manifest\\.GUID should equal \"([^\"]*)\"$")
    public void manifest_GUID_should_equal(String guid) throws Throwable {
        JSONObject json = manifest.get();
        assertEquals((String)json.get("GUID"),guid);
    }

    @Given("^the current time and a connection to the internet$")
    public void the_current_time_and_a_connection_to_the_internet() throws Throwable {
        // assumed
    }

@When("^called$")
public void called() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

@Then("^returned value should be greater than (\\d+)$")
public void returned_value_should_be_greater_than(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

@Then("^the returned value should be a valid Bitcoin block hash$")
public void the_returned_value_should_be_a_valid_Bitcoin_block_hash() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

@When("^asked for the most recent Ethereum block number$")
public void asked_for_the_most_recent_Ethereum_block_number() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

@When("^asked for the most recent Ethereum block hash$")
public void asked_for_the_most_recent_Ethereum_block_hash() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

@Then("^the returned value should be a valid Ethereum block hash$")
public void the_returned_value_should_be_a_valid_Ethereum_block_hash() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

}
