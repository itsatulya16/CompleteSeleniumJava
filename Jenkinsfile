pipeline {
    agent { label 'vm' }

    tools {
        jdk "jdk"
        maven "3.9.6"
    }

    stages {

        stage('clean up') {
            steps {
                bat 'mvn -B -DskipTests clean package'
            }
        }

        stage('code compile') {
            steps {
                bat "mvn compile"
            }
        }

        stage('test execute') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat "mvn test"
                }
            }
        }
    }

    post {
        always {
            // Publish HTML report even if the test stage failed
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'Reports', reportFiles: 'Spark.html', reportName: 'ExtentHTMLReport', reportTitles: '', useWrapperFileDirectly: true])

            // Clean workspace
            cleanWs()
        }
    }
}
