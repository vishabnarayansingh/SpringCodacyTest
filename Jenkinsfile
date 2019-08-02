pipeline {
   agent { label 'ssh-slave1' }
    environment {
       NEXUS_VERSION = "nexus3"
       NEXUS_PROTOCOL = "http"
       NEXUS_URL = "10.131.155.57:8081"
       NEXUS_REPOSITORY = "vn-repository"
       NEXUS_CREDENTIAL_ID = "nexus"
       NEXUS_PRIVATE_REPO_URL ="10.131.155.57"
       NEXUS_REPO_PORT = "8081"
    }
    stages {
  	
        stage("Build"){
	    agent {
		docker {
		    image 'maven:3-alpine'
		    args '-v $HOME/.m2:/root/.m2'
		}
	    } 
            steps{
               sh "mvn clean package -DskipTests" 
            }
	    post {
		failure {
		    script {
			echo "TestRail failed"
		    }
		}
    	    }
        }
        stage('Publish To Nexus'){
		steps{
		    script{
			pom = readMavenPom file: "pom.xml";
			filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
			    artifactPath = filesByGlob[0].path;
			    artifactExists = fileExists artifactPath;
			    if(artifactExists) {
				    echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
				    nexusArtifactUploader artifacts: 
				    [
					[artifactId: pom.artifactId, classifier: '', file: artifactPath, type: pom.packaging]
				    ], 
				    credentialsId: NEXUS_CREDENTIAL_ID, 
				    groupId: pom.groupId, 
				    nexusUrl: NEXUS_URL, 
				    nexusVersion: NEXUS_VERSION, 
				    protocol: NEXUS_PROTOCOL, 
				    repository: NEXUS_REPOSITORY, 
				    //version: pom.version
				    //version: "${params.TAG_VERSION}"
				    version: "${BUILD_NUMBER}"
				    // version: "${pom.version}-${BUILD_NUMBER}-${env.OWN_GIT_HASH}"
				echo '********* Done Publish to NEXUS OSS ************** ' 
			    }else {
			    error "*** File: ${artifactPath}, could not be found";
			    }
			}

			} 
		}
    }
}
	
	
	
	
	 /*  stage('Deploy To Nexus') {
            steps {
                agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
                }    
                
                 configFileProvider([configFile(fileId: 'bf894b35-0554-479b-9521-187b8545178d', variable: 'MAVEN_GLOBAL_SETTINGS')]) {
                    echo "##### $MAVEN_GLOBAL_SETTINGS}"
                    sh 'mvn -gs $MAVEN_GLOBAL_SETTINGS deploy'
                } 
                
                 withMaven(globalMavenSettingsConfig: 'ae44493d-0903-4274-b6f4-ef1995f2e0af'){
                     //mavenSettingsConfig
                     //globalMavenSettingsConfig
                   // sh 'mvn deploy -Dmaven.test.skip=true'
                  //   sh "mvn clean deploy -s /root/.m2/nexusmaven-settings.xml"
                    //sh "mvn clean deploy -s /root/.m2/conf/settings.xml"
                   //  echo "##### ${WORKSPACE}"
                   //  sh 'export PATH=$MVN_CMD:$PATH && mvn help:effective-settings'
                   //  sh "mvn -Dmaven.repo.local=${WORKSPACE}/.repository clean deploy -DskipTests=true -s /root/.m2/settings.xml"
                    //sh "mvn -Dmaven.repo.local=${WORKSPACE}/.repository clean deploy -DskipTests=true -s /root/.m2/settings.xml"
		 }
         
                    
                 }
            } */
