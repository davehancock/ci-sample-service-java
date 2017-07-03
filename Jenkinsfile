pipeline {
    agent any

    stages{

        stage ('Stage Checkout') {
             steps {
                checkout scm
             }
        }

        stage ('Test') {
            steps {
                sh "./gradlew clean test"
            }
            post {
                always {
                    junit "**/build/test-results/**/TEST-*.xml"
                }
            }
        }

        stage ('Build') {
            steps {
                sh "./gradlew clean build"
            }
        }
    }
}
