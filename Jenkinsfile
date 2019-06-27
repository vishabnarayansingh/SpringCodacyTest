pipeline{
	agent{
		label "master"
	}
	tools{
		maven "Maven-3.3.9"
		jdk "JAVA_HOME"
	}
	environment {
		CODACY_PROJECT_TOKEN = credentials('CODACY_TAVANT_GITHUB')
		DOCKER = credentials('DOCKER-HUB-CREDENTIALS')
		//CODACY_API_BASE_URL="http://10.131.146.120:16006"
	}
	options {
		skipDefaultCheckout true
	}
	stages{
		stage('Checkout'){
			steps{
				script{
					def scmVar = checkout([$class: 'GitSCM', branches: [[name: '*/codacy']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/naryansingh/SpringCodacyTest.git']]])
					echo "${scmVar}"
					echo "${scmVar.GIT_COMMIT}"
					env.GIT_COMMIT = scmVar.GIT_COMMIT
					echo "${env.GIT_COMMIT}"
				}
			}
		}
		stage('Build'){
			steps{
				sh  "mvn clean package -DskipTests"
			}
		}
		stage('Test') {
			steps {
				sh 'mvn test surefire-report:report'
			}
			post {
				failure {
					script {
						currentBuild.result = 'FAILURE'
					}
					sendMail('Test Build Failed !!!!!')
				}

				always {
					junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
					jacoco classPattern: '**/target/classes', execPattern: '**/target/**.exec'
					publishHTMLReports('target/surefire-reports/', 'surefire-report.html', 'Surefire-Report');
					publishHTMLReports('target/site/jacoco/', 'index.html', 'Jacoco-Coverage-Report');

				}
			}
		}
		stage('SonarQube Analysis'){
			steps{
				withSonarQubeEnv('Sonar6.7') {
					sh  "mvn sonar:sonar"
				}
				script{
					timeout(time:20, unit: 'MINUTES') {
						def qg = waitForQualityGate abortPipeline: true
						if (qg.status != 'OK') {
							error "Pipeline aborted due to quality gate failure: ${qg.status}"
						}
					}
				}
			}
			post {
				failure {
					script {currentBuild.result = 'FAILURE'}
					sendMail('Sonar Quality Gate Failed !!!')
				}
			}
		}
		stage('Upload Test Coverage For Codacy '){
			steps{
				sh '''
                    curl -Ls -o codacy-coverage-reporter "$(curl -Ls https://api.github.com/repos/codacy/codacy-coverage-reporter/releases/latest |
                    jq -r '.assets |
                    map({name, browser_download_url} |
                    select(.name | contains("codacy-coverage-reporter-linux"))) |
                    .[0].browser_download_url')"
                 '''
				sh "chmod +x codacy-coverage-reporter"
				sh "./codacy-coverage-reporter report -l Java -r target/site/jacoco/jacoco.xml"


			}
			post {
				failure {
					script{
						currentBuild.result = 'FAILURE'
					}
					sendMail('Upload Coverage for Codacy !!!!! ')
				}
			}
		}
		/* stage('Spotbugs'){
            steps{
                sh "curl -L https://github.com/codacy/codacy-analysis-cli/archive/master.tar.gz | tar xvz"
                sh "cd codacy-analysis-cli-* && make install"
                sh "codacy-analysis-cli analyse  --project-token 4572774becfe4a89963dc16b2e500a69 --tool SpotBugs  --directory /src/main --upload --verbose"
                //sh "codacy-analysis-cli analyse --directory src/main --project-token 4572774becfe4a89963dc16b2e500a69 --allow-network --codacy-api-base-url http://10.131.146.120 --upload --verbose"
            }
        } */

		/* stage('Build Docker Image') {
             steps {
                 echo 'Building Docker Image ...'
                 sh "mvn -DskipTests=true dockerfile:build"
             }
             post {
                 failure {
                     echo 'Docker Build Image  has  failed. See logs for details.'
                     script {
                         currentBuild.result = 'FAILURE'
                     }
                     sendMail('Build Docker Image Failed !!!!!!!')
                 }
             }
         }*/

	}
	post {
		success{
			sendMail('All Stages Executed Sucessfully !!')
		}
	}
}
def publishHTMLReports(reportDirectory,reportFileName,reportName) {
	publishHTML([allowMissing         : false,
				 alwaysLinkToLastBuild: false,
				 keepAll              : false,
				 reportDir            : reportDirectory,
				 reportName           : reportName,
				 reportFiles          : reportFileName
	])

}
def sendMail(mail_subject ){
	emailext attachLog: false,
			body: '${JELLY_SCRIPT,template="jenkins-html-custom"}',
			recipientProviders: [developers(), requestor()],
			mimeType: 'text/html' ,
			subject: mail_subject
}


