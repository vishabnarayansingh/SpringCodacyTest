pipeline {
    //agent none
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages {
      /*  stage("Build"){
            steps{
               sh "mvn clean package -DskipTests" 
            }
        } */
        stage('Deploy') {
            steps {
                  /*agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
                } */     
                
                /*configFileProvider([configFile(fileId: 'bf894b35-0554-479b-9521-187b8545178d', variable: 'MAVEN_GLOBAL_SETTINGS')]) {
                    sh 'mvn -gs $MAVEN_GLOBAL_SETTINGS deploy'
                }*/
                
                 withMaven(globalMavenSettingsConfig: 'ae44493d-0903-4274-b6f4-ef1995f2e0af'){
                     //mavenSettingsConfig
                     //globalMavenSettingsConfig
                   // sh 'mvn deploy -Dmaven.test.skip=true'
                  //   sh "mvn clean deploy -s /root/.m2/nexusmaven-settings.xml"
                    //sh "mvn clean deploy -s /root/.m2/conf/settings.xml"
                     echo "##### ${WORKSPACE}"
                     sh 'export PATH=$MVN_CMD:$PATH && mvn help:effective-settings'
                     sh "mvn -Dmaven.repo.local=${WORKSPACE}/.repository clean deploy -DskipTests=true -s /root/.m2/settings.xml"
                    //sh "mvn -Dmaven.repo.local=${WORKSPACE}/.repository clean deploy -DskipTests=true -s /root/.m2/settings.xml"
                                       
                 }
            }
        }
        
    }
}
