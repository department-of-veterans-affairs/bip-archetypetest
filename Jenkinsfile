mavenGitflowPipeline {

    skipSonar = true
    skipFortify = true
    skipMavenDeploy = false
    skipFunctionalTests = true
    skipPerformanceTests = true

    //Sonar Github Credentials - Settings this value will configure the pipeline to use this credential
    //to connect to github during sonar PR scans, adding comments for any violations found
    sonarGithubCredentials = 'dsva-github'

    //Github credential ID to use for releases
    githubCredentials = 'epmo-github'
    
    //Specify to use the fortify maven plugin, instead of the Ant task to execute the fortify scan
    useFortifyMavenPlugin = true

    /*************************************************************************
    * Docker Build Configuration
    *************************************************************************/

    // Map of Image Names to sub-directory in the repository. If this is value is non-empty, 
    // the build pipeline will build all images specified in the map. The example below will build an image tagged as 
    // `bip-archetypetest:latest` using the Docker context of `./bip-archetypetest`.
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
   // postmanFolder = 'smoke'

   // Environment File. Optional
   // postmanEnvironment = 'src/test/postman/dev.json'

   // Globals File. Optional.
   // postmanGlobals = 'src/test/postman/globals.json'

   // Data File. Optional.
   // postmanData = 'src/test/postman/data.json'

    /*************************************************************************
    * OpenShift Deployment Configuration
    *
    * This section only applied to builds running on the OpenShift platform.
    * This section should be omitted if you are using Helm for deployments on
    * Kubernetes.
    *************************************************************************/
    //Path to your applications Openshift deployment template
    deploymentTemplates = ["template.yaml"]
    
    //Deployment parameters used to configure your Openshift deployment template
    deploymentParameters = [
        'APP_NAME': 'bip-archetypetest',
        'IMAGE': 'bip-archetypetest',
        'SPRING_PROFILES': 'dev'
    ]

    //Functional Testing Deployment parameters used to configure your Openshift deployment template
    // Defaults to the value of `deploymentParameters` if not specified.
    functionalTestDeploymentParameters = [
        'APP_NAME': 'bip-archetypetest',
        'IMAGE': 'bip-archetypetest',
        'SPRING_PROFILES': 'dev'
    ]

    //Performance Testing Deployment parameters used to configure your Openshift deployment template
    // Defaults to the value of `deploymentParameters` if not specified.
    performanceTestDeploymentParameters = [
         'APP_NAME': 'bip-archetypetest',
         'IMAGE': 'bip-archetypetest',
         'SPRING_PROFILES': 'dev'
    ]

    /*************************************************************************
    * Helm Deployment Configuration
    *
    * This section only applied to builds running on the Kubernetes platform.
    * This section should be omitted if you are using Openshift templates for
    * deployment on Openshift.
    *************************************************************************/

    //Git Repository that contains your Helm chart
    chartRepository = "https://github.ec.va.gov/EPMO/bip-archetypetest-config"

    //Path to your chart directory within the above repository
    chartPath = "charts/bip-archetypetest"

    //Jenkins credential ID to use when connecting to repository. This defaults to `github` if not specified
    chartCredentialId = "github"

    //Value YAML file used to configure the Helm deployments used for functional and performance testing.
    chartValueFunctionalTestFile = "testing.yaml"
    chartValuePerformanceTestFile = "testing.yaml"

    //Release name to use
    chartReleaseName = "bip-archetypetest"
}
