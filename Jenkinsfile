pipeline {
    agent any

    environment {
        JAVA_HOME = "C:\\Program Files\\Java\\jdk-17"
        MAVEN_HOME = "C:\\Program Files\\apache-maven-3.9.6-bin\\apache-maven-3.9.6"
        ALLURE_HOME = "C:\\allure-2.32.0\\allure-2.32.0"
        PATH = "${JAVA_HOME}\\bin;${MAVEN_HOME}\\bin;${ALLURE_HOME}\\bin;${env.PATH}"
    }

    stages {


        stage('Test Execute') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'bat 'mvn clean test -Dallure.results.directory=allure-results'
'
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                dir('target') {
                    bat 'allure generate ../allure-results --clean -o allure-report'
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/allure-report/**', fingerprint: true
            }
        }
    }

    post {
        always {
            // ADD THIS
            allure([
                includeProperties: false,
                jdk: '',
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']]
            ])
        }
    }
}
