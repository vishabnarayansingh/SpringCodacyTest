pipeline {
   agent any
  // agent { label 'testnodessh' }
	
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
	       sh 'echo Build Complete'
            }
	    post {
		failure {
		    script {
			echo "TestRail failed"
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
			 withMaven(mavenSettingsConfig: 'ae44493d-0903-4274-b6f4-ef1995f2e0af'){		 
			  //sh "mvn -f pom.xml clean deploy -DskipTests=true"
			    //sh "mvn clean deploy -s /root/.m2/conf/settings.xml"
			   //  sh 'export PATH=$MVN_CMD:$PATH && mvn help:effective-settings'
			   //    sh "export PATH=$MVN_CMD_DIR:$PATH && clean deploy -DskipTests=true"
			 }   
                 }
            } */
