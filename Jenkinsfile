pipeline {
    agent any

    environment {
        JAVA_HOME = "C:\\Program Files\\Java\\jdk-17"
        MAVEN_HOME = "C:\\Program Files\\apache-maven-3.9.6-bin\\apache-maven-3.9.6"
        ALLURE_HOME = "C:\\allure-2.32.0\\allure-2.32.0"
        PATH = "${JAVA_HOME}\\bin;${MAVEN_HOME}\\bin;${ALLURE_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Clean Up') {
            steps {
                bat 'mvn -B -DskipTests clean package'
            }
        }

        stage('Code Compile') {
            steps {
                bat 'mvn compile'
            }
        }

        stage('Test Execute') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'mvn test'
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
          bat 'mvn allure:aggregate'
          bat 'allure generate target/allure-results -o target/allure-report'
            }
        }
    }

    post {
        always {
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: false,
                reportDir: 'target/allure-report',
                reportFiles: 'index.html',
                reportName: 'Allure Test Report',
                reportTitles: ''
            ])
        }
    }
}