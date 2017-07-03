pipeline {

    agent any

    docker.image('openjdk:8-jdk').inside {

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

}
