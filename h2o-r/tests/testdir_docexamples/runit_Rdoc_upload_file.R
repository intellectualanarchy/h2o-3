setwd(normalizePath(dirname(R.utils::commandArgs(asValues=TRUE)$"f")))
source("../../scripts/h2o-r-test-setup.R")



test.rdocuploadfile.golden <- function() {
	
prostate.hex <- h2o.uploadFile(path = locate("smalldata/extdata/prostate.csv"), destination_frame = "prostate.hex")
summary(prostate.hex)


}

doTest("R Doc upload file", test.rdocuploadfile.golden)

