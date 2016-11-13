package provenj;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;
import org.json.simple.JSONObject;
import java.util.UUID;

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
    public void manifest_FileName_should_equal(String fileName) throws Throwable {
	if ( fileName != (String) manifest.get().get("FileName")) {
            throw new RuntimeException();
	}
    }

    @Then("^manifest\\.BitcoinBlockNumber should be (\\d+)$")
    public void manifest_BitcoinBlockNumber_should_be(int blockNumber) throws Throwable {
	if ( blockNumber != (int) manifest.get().get("BitcoinBlockNumber")) {
            throw new RuntimeException();
	}
    }

    @Then("^manifest\\.BitcoinBlockHash should equal \"([^\"]*)\"$")
    public void manifest_BitcoinBlockHash_should_equal(String blockHash) throws Throwable {
	if (!blockHash.equals((String) manifest.get().get("BitcoinBlockHash"))) {
            throw new RuntimeException();
	}
    }

    @Then("^manifest\\.EthereumBlockNumber should equal (\\d+)$")
    public void manifest_EthereumBlockNumber_should_equal(int blockNumber) throws Throwable {
	if (blockNumber != (int) manifest.get().get("EthereumBlockNumber")) {
            throw new RuntimeException();
	}
    }

    @Then("^manifest\\.EthereumBlockHash should equal \"([^\"]*)\"$")
    public void manifest_EthereumBlockHash_should_equal(String blockHash) throws Throwable {
	if (!blockHash.equals((String) manifest.get().get("EthereumBlockHash"))) {
            throw new RuntimeException();
	}
    }

    @Then("^manifest\\.PreviousIFPSHash should equal \"([^\"]*)\"$")
    public void manifest_PreviousIFPSHash_should_equal(String ifpsHash) throws Throwable {
	if (!ifpsHash.equals((String) manifest.get().get("PreviousIPFSHash"))) {
            throw new RuntimeException();
	}
    }

    @Then("^manifest\\.PreviousFileHashes should equal \"([^\"]*)\"$")
    public void manifest_PreviousFileHashes_should_equal(String fileHashes) throws Throwable {
	if (!fileHashes.equals((String) manifest.get().get("PreviousFileHashes"))) {
            throw new RuntimeException();
	}
    }

    @Then("^manifest\\.GUID should equal \"([^\"]*)\"$")
    public void manifest_GUID_should_equal(String guid) throws Throwable {
	if (!guid.equals((String) manifest.get().get("GUID"))) {
            throw new RuntimeException();
	}
    }
}
