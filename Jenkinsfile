pipeline {

    agent any

        stages{

            docker.image('openjdk:8-jdk').inside {

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

}
