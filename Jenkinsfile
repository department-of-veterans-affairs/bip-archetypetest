mavenGitflowPipeline {

    useBranchNameTag = true
    skipTests = false
    skipFunctionalTests = false
    skipPerformanceTests = false
    skipSonar = false
    skipFortify = false
    skipTwistlock = false

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

    //TODO_CMF: Commenting out while testing AF-1490
//     branchToDeployEnvMap = [
//            'master': ['lower':['test'],'upper':[[
//                'env':'uat',
//                'source_repository': 'https://container-registry.dev8.bip.va.gov',
//                'target_repository': 'https://container-registry.stage8.bip.va.gov',
//                'source_credential': 'docker-registry',
//                'target_credential': 'staging-docker-registry'
//            ]]],
//            'development': ['lower':['dev'],'upper':[[
//                'env':'ivv',
//                'source_repository': 'https://container-registry.dev8.bip.va.gov',
//                'target_repository': 'https://container-registry.stage8.bip.va.gov',
//                'source_credential': 'docker-registry',
//                'target_credential': 'staging-docker-registry'
//            ],[
//                'env':'demo',
//                'source_repository': 'https://container-registry.dev8.bip.va.gov',
//                'target_repository': 'https://container-registry.stage8.bip.va.gov',
//                'source_credential': 'docker-registry',
//                'target_credential': 'staging-docker-registry'
//              ]
//            ]],
//        ]

    /*************************************************************************
    * Functional Testing Configuration
    *************************************************************************/
    
    //Directory that contains the cucumber reports
    cucumberReportDirectory = "bip-archetypetest/target/site"

    //Additional Maven options to use when running functional test cases. By default, security policy tests are
    // ignored, but when OPA is enabled, the subsequent line overrides the cucumberOpts to allow them to be included.
    cucumberOpts = "--tags @DEV --tags ~@securitypolicy"

    cucumberOpts = "--tags @DEV"

    /* Postman Testing Configuration */
   
   // Set of Postman test collections to execute. Required for Postman Testing stage to run.
   postmanTestCollections = [
     'bip-archetypetest/src/inttest/resources/bip-archetypetest.postman_collection.json'
   ]

   // Only run specified folder from collection. Optional. Runs all tests in collection if not specified
   // postmanFolder = 'token'

   // Environment File. Optional
   // postmanEnvironment = 'bip-archetypetest/src/inttest/resources/bip-archetypetest-test.postman_environment.json'

   // Globals File. Optional.
   // postmanGlobals = 'bip-archetypetest/src/inttest/resources/bip-archetypetest.postman_globals.json'

   // Data File. Optional.
   // postmanData = 'bip-archetypetest/src/inttest/resources/bip-archetypetest.postman_data.csv'

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
