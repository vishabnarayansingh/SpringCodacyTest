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

	}
	options {
		skipDefaultCheckout true
	}

	stages{
		stage('CheckOut'){
			steps{
				sh "git clean -fdx"
				script{
					def scm = checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/naryansingh/SpringCodacyTest.git']]])
					echo "${scm}"
					env.GIT_COMMIT = scm.GIT_COMMIT
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
//				sh "./codacy-coverage-reporter report -l Java -r target/site/jacoco/jacoco.xml"
				sh "./codacy-coverage-reporter report -l Java --commit-uuid ${env.GIT_COMMIT} -r target/site/jacoco/jacoco.xml"


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


