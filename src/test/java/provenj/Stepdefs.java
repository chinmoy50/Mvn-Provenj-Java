package provenj;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;


public class Stepdefs {
    // Meaningless templates from cucumber skeleton for Belly
    @Given("^I have (\\d+) cukes in my belly$")
    public void I_have_cukes_in_my_belly(int cukes) throws Throwable {
        Belly belly = new Belly();
        belly.eat(cukes);
    }
    @When("^I wait (\\d+) hour$")
    public void i_wait_hour(int arg1) throws Throwable {
    } 
    @Then("^my belly should growl$")
    public void my_belly_should_growl() throws Throwable {
    }


    // Build manifest

    @Given("^a JPEG file named \"([^\"]*)\"$")
    public void a_JPEG_file_named(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the current Bitcoin block number btc_block$")
    public void the_current_Bitcoin_block_number_btc_block() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the current Bitcoin block hash btc_hash$")
    public void the_current_Bitcoin_block_hash_btc_hash() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the current Ethereum block number eth_block$")
    public void the_current_Ethereum_block_number_eth_block() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the current Ethereum block hash eth_hash$")
    public void the_current_Ethereum_block_hash_eth_hash() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the IPFS hash from the last submitted file hash_ipfs$")
    public void the_IPFS_hash_from_the_last_submitted_file_hash_ipfs() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the other hashes from the last submitted file file_hashes$")
    public void the_other_hashes_from_the_last_submitted_file_file_hashes() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the GUID for the submission$")
    public void the_GUID_for_the_submission() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I ask for a manifest file$")
    public void i_ask_for_a_manifest_file() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^manifest\\.FileName should equal \"([^\"]*)\"$")
    public void manifest_FileName_should_equal(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^manifest\\.BitcoinBlockNumber should match btc_block$")
    public void manifest_BitcoinBlockNumber_should_match_btc_block() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^manifest\\.BitcoinLastBlockHash should equal btc_hash$")
    public void manifest_BitcoinLastBlockHash_should_equal_btc_hash() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
}

    @Then("^manifest\\.EthereumBlockNumber should equal eth_block$")
    public void manifest_EthereumBlockNumber_should_equal_eth_block() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^manifest\\.EthereumLastBlockHash should equal eth_hash$")
    public void manifest_EthereumLastBlockHash_should_equal_eth_hash() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^manifest\\.PreviousIFPSHash should equal hash_ipfs$")
    public void manifest_PreviousIFPSHash_should_equal_hash_ipfs() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^manifest\\.FileHashes should equal file_hashes$")
    public void manifest_FileHashes_should_equal_file_hashes() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^manifest\\.GUID should equal GUID$")
    public void manifest_GUID_should_equal_GUID() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
