pipeline {
    agent none
    stages {
        stage('Build & Deploy') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                   configFileProvider([configFile(fileId: 'my-maven-settings', variable: 'SETTINGS')]) {
                    sh "mvn -s $SETTINGS deploy -DskipTests"
                }
                
                 withMaven(mavenSettingsConfig: 'my-maven-settings'){
                    //sh 'mvn deploy -Dmaven.test.skip=true'
                      sh "mvn -s settings.xml clean deploy"
                 }
            }
        }
        
    }
}
