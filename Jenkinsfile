pipeline {
    //agent none
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages {
        stage("Build"){
            steps{
               sh "mvn clean package -DskipTests" 
            }
        }
        stage('Deploy') {
            steps {
                  /*agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
                } */           
                 withMaven(mavenSettingsConfig: 'maven-settings'){
                     //mavenSettingsConfig
                     //globalMavenSettingsConfig
                   // sh 'mvn deploy -Dmaven.test.skip=true'
                  //   sh "mvn clean deploy -s /root/.m2/nexusmaven-settings.xml"
                    //sh "mvn clean deploy -s /root/.m2/conf/settings.xml"
                     echo "##### ${WORKSPACE}"
                     echo "#####GLOBAL ${M2_HOME}.m2"
                     sh "mvn -Dmaven.repo.local=${WORKSPACE}/.repository clean deploy -DskipTests=true -s /root/.m2/MyGlobalSettings-settings.xml"
                    //sh "mvn -Dmaven.repo.local=${WORKSPACE}/.repository clean deploy -DskipTests=true -s /root/.m2/settings.xml"
                                       
                 }
            }
        }
        
    }
}
