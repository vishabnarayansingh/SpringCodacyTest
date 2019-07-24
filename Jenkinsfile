pipeline {
    agent none
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                 withMaven(options: [artifactsPublisher(disabled: true)]){
                    sh 'mvn test surefire-report:report'
                 }
            }
        }
        stage('JDK') {
            agent { docker 'openjdk:8-jre' } 
            steps {
                echo 'Hello, JDK'
                sh 'java -version'
            }
        }
        stage("INSIDE"){
            steps{
               docker.image('maven:3.3.3-jdk-8').inside {
                  git 'https://github.com/vishabnarayansingh/SpringCodacyTest.git'
                  sh 'mvn -B clean install'
                }   
            }
        }
    }
}
