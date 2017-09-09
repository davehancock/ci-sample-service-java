pipeline {
    agent none

    environment {
        GRADLE_USER_HOME = '/cache/gradle'
    }

    stages {
        stage('Package') {

            agent {
                docker {
                    image 'openjdk:8-jdk'
                    args '-v /cache:/cache'
                }
            }

            steps {

                echo "Hello"

                script {
                    gitVars = checkout scm

                    sh './gradlew compileJava'
                    sh './gradlew assemble'

                    dockerBuild = docker.build "daves125125/ci-sample-service:${gitVars["GIT_COMMIT"]}"
                }
            }
        }
    }

}
