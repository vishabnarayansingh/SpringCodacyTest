pipeline {
   agent any
    stages {
		stage('Deploy To Nexus') {
			agent {
				docker {
					image 'maven:3-alpine'
					args '-v $HOME/.m2:/root/.m2'
				}
			}
			steps {

				withMaven(mavenSettingsConfig: 'nexus-server-id'){
					//sh "mvn -f pom.xml clean deploy -DskipTests=true"
					//sh "mvn clean deploy -s /root/.m2/conf/settings.xml"
					// sh 'export PATH=$MVN_CMD:$PATH && mvn help:effective-settings'
					sh "mvn clean package -DskipTests"
					sh 'echo Build Complete'
					 sh "export PATH=$MVN_CMD_DIR:$PATH && mvn deploy -DskipTests=true"
				}
			}
		}
    }
}
