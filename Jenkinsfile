pipeline {
    agent none
    environment {
       NEXUS_CREDENTIALS = credentials('nexus')
    }
    stages {
        stage('Build & Deploy') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                
                 withMaven(globalMavenSettingsConfig: 'maven-settings'){
                    
                     //sh "mvn clean package -DskipTests"
                    //sh 'mvn deploy -Dmaven.test.skip=true'
                    //  sh "mvn clean deploy -s /root/.m2/settings.xml"
                    //  sh "mvn clean deploy -s /root/.m2/conf/settings.xml"
                    echo "############### MAVEN DEPLOY ##########"
                    sh "mvn -Dmaven.repo.local=${WORKSPACE}/.repository clean deploy -DskipTests=true -s /root/.m2/settings.xml"
                                       
                 }
            }
        }
        
    }
}
