pipeline {
    agent any

    tools {
        jdk "jdk"
        maven "3.9.6"
    }

    stages {

//         stage('clone Repository') {
//             steps {
//                 git credentialsId: 'bef29e93-1930-40d6-a967-ae4c2dbf7f01', url: 'https://gitlab.com/atulambade/completselenium.git'
//             }
//         }

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
                bat "mvn test"
            }
            post {
                            always {
                                junit 'target/surefire-reports/testng-results.xml'
                            }
                        }
        }

        stage('publish reports') {
            steps{
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target', reportFiles: 'Spark.html', reportName: 'ExtentHTMLReport', reportTitles: '', useWrapperFileDirectly: true])
            }
        }

         stage('clean worksplace') {
            steps {
               cleanWs()
            }
        }
    }
}
