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
        }

        stage ('Build') {
            steps {
                sh "./gradlew clean build"
            }
        }
    }

}
