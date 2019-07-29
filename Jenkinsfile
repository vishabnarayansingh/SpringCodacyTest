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
  
         stage('STEP') {
            input {
                message "Should we continue?"
                ok "OKK"
                submitter "Vishab Singh"
                parameters {
                    string(name: 'PERSON', defaultValue: 'Jenkins Input ', description: 'Who should I say hello to?')
                }
            }
            steps {
                echo "Hello, ${PERSON}, nice to meet you."
            }
        }
        
    }
}
