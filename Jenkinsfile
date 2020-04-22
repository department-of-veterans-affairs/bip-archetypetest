mavenGitflowPipeline {

    //Specify to use the fortify maven plugin, instead of the Ant task to execute the fortify scan
    useFortifyMavenPlugin = true

    /*************************************************************************
    * Docker Build Configuration
    *************************************************************************/

    // Map of Image Names to sub-directory in the repository. If this is value is non-empty, 
    // the build pipeline will build all images specified in the map. The example below will build an image tagged as 
    // `archetypetest/bip-archetypetest:latest` using the Docker context of `./bip-archetypetest`.
    dockerBuilds = [
        'archetypetest/bip-archetypetest': 'bip-archetypetest'
    ]

    /*************************************************************************
    * Functional Testing Configuration
    *************************************************************************/
    
    //Directory that contains the cucumber reports
    cucumberReportDirectory = "bip-archetypetest-inttest/target/site"

    //Additional Mavn options to use when running functional test cases
    cucumberOpts = "--tags @DEV"
    
    /* Postman Testing Configuration */
   
   // Set of Postman test collections to execute. Required for Postman Testing stage to run.
   postmanTestCollections = [
     'bip-archetypetest-inttest/src/inttest/resources/bip-archetypetest.postman_collection.json'
   ]

   // Only run specified folder from collection. Optional. Runs all tests in collection if not specified
   // postmanFolder = 'token'

   // Environment File. Optional
   // postmanEnvironment = 'bip-archetypetest-inttest/src/inttest/resources/bip-archetypetest-test.postman_environment.json'

   // Globals File. Optional.
   // postmanGlobals = 'bip-archetypetest-inttest/src/inttest/resources/bip-archetypetest.postman_globals.json'

   // Data File. Optional.
   // postmanData = 'bip-archetypetest-inttest/src/inttest/resources/bip-archetypetest.postman_data.csv'

   // Number of Iterations to run tests. Optional.
   // postmanIterationCount = 3

    /*************************************************************************
    * Helm Deployment Configuration
    *
    * This section only applied to builds running on the Kubernetes platform.
    * This section should be omitted if you are using Openshift templates for
    * deployment on Openshift.
    *************************************************************************/

    //Git Repository that contains your Helm chart
    chartRepository = "https://github.ec.va.gov/EPMO/bip-archetypetest-config"

    //Git branch to obtain Helm chart from
    chartBranch = "development"

    //Path to your chart directory within the above repository
    chartPath = "charts/bip-archetypetest"

    //Jenkins credential ID to use when connecting to repository. This defaults to `github` if not specified
    chartCredentialId = "github"

    //Value YAML file used to configure the Helm deployments used for functional and performance testing.
    chartValueFunctionalTestFile = "testing.yaml"
    chartValuePerformanceTestFile = "testing.yaml"

    //Value YAML file used to configure the Helm deployments used for the Deploy Review Instance stage
    chartValueReviewInstanceFile = "reviewInstance.yaml"

    //Release name to use
    chartReleaseName = "bip-archetypetest"
}
