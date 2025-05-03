pipeline {
    agent any

//     tools {
//         jdk "jdk"
//         maven "3.9.6"
//     }

 environment {
        JAVA_HOME = "/C:/Program Files/Java/jdk-17"   // Replace with your Java installation path
        MAVEN_HOME = "/C:/Program Files/apache-maven-3.9.6-bin/apache-maven-3.9.6" // Replace with your Maven installation path
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
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
