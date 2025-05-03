pipeline {
    agent any

 environment {
     JAVA_HOME = "C:\\Program Files\\Java\\jdk-17"
     MAVEN_HOME = "C:\\Program Files\\apache-maven-3.9.6-bin\\apache-maven-3.9.6"
     ALLURE_HOME = "C:\\allure-2.32.0\\allure-2.32.0"
     PATH = "${JAVA_HOME}\\bin;${MAVEN_HOME}\\bin;${ALLURE_HOME}\\bin;${env.PATH}"
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
        stage('Generate Allure Report') {
                    steps {
                        bat 'allure generate --single-file allure-results'
                    }
                }
    }

//     post {
//         always {
//             // Publish HTML report even if the test stage failed
//             publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'Reports', reportFiles: 'Spark.html', reportName: 'ExtentHTMLReport', reportTitles: '', useWrapperFileDirectly: true])
//
//             // Clean workspace
//             cleanWs()
//         }
//     }

//         post {
//                 always {
//                     allure([
//                         results: [[path: 'target/allure-results']]
//                     ])
//                 }
//             }

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
