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
                   /*configFileProvider([configFile(fileId: 'my-maven-settings', variable: 'SETTINGS')]) {
                    sh "mvn -s $SETTINGS deploy -DskipTests"
                }*/
              
                 withMaven(){
                     //globalMavenSettingsConfig: 'my-maven-settings'
                    //sh 'mvn deploy -Dmaven.test.skip=true'
                    //  sh "mvn clean deploy -s /root/.m2/my-settings.xml"
                    //  sh "mvn clean deploy -s /root/.m2/conf/settings.xml"
                    sh "mvn -Dmaven.repo.local=${WORKSPACE}/.repository clean deploy -DskipTests=true -s /root/.m2/my-settings.xml"
                                       
                 }
            }
        }
        
    }
}
